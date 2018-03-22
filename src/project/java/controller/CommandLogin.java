package project.java.controller;

import project.java.dao.dao.DAO;
import project.java.entity.City;
import project.java.entity.User;
import project.java.filters.CookiesUser;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class CommandLogin extends Action {
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, IOException, InvalidKeyException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.setAttribute(Message.MESSAGE, "Вы уже вошли");
        }
        if (!FormUtil.isPost(request)) {
            return null;
        } else if (request.getParameter("Login").equals("")) {
            request.setAttribute(Message.MESSAGE, "Введите имя пользователя и пароль");
            return null;
        }
        String login = "";
        try {
            login = FormUtil.getString(request.getParameter("Login"), "[A-Za-z0-9_@.-]+");
        } catch (ParseException e) {
            request.setAttribute(Message.MESSAGE, "Введены недопустимые символы");
            return null;
        }

        DAO dao = DAO.getDAO();
        List<User> users = dao.user.getAll(String.format("WHERE login='%s'", login));
        if (users.size() == 1) {
            User user = users.get(0);
            String password = FormUtil.getString(request.getParameter("Password"), "[A-Za-z0-9_А-Яа-яЁё]+");
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(1);
                CookiesUser.setCookie(response, user);
                List<City> cities = DAO.getDAO().city.getAll();
                request.setAttribute("cities", cities);
                return Actions.PROFILE.command;
            } else {
                request.setAttribute(Message.MESSAGE, "Неверный пароль");
                return null;
            }
        } else {
            request.setAttribute(Message.MESSAGE, "Пользователя с таким именем не существует");
            return null;
        }
    }
}
