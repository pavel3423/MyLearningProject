package project.java.controller;

import project.java.dao.dao.DAO;
import project.java.entity.Brand;
import project.java.entity.City;
import project.java.entity.Role;
import project.java.entity.User;
import project.java.filters.CookiesUser;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class CommandProfile extends Action {
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute(Message.MESSAGE, "Войдите чтобы просмотреть профиль");
            return Actions.LOGIN.command;
        }
        if (user.getRolesID() == 1) {
            List<User> users = DAO.getDAO().user.getAll();
            request.setAttribute("users", users);
        }
        List<Brand> brands = DAO.getDAO().brand.getAll();
        request.setAttribute("brands", brands);
        List<City> cities = DAO.getDAO().city.getAll();
        request.setAttribute("cities", cities);
        List<Role> roles = DAO.getDAO().role.getAll();
        request.setAttribute("roles", roles);
        if (FormUtil.isPost(request)) {
            User userNew = null;
            try {
                if (request.getParameter("button") != null) {
                    userNew = user;
                } else if (request.getParameter("submit") != null && user.getRolesID() == 1) {
                    userNew = DAO.getDAO().user.read(Integer.parseInt(request.getParameter("submit")));
                } else if (request.getParameter("delete") != null && user.getRolesID() == 1) {
                    userNew = DAO.getDAO().user.read(Integer.parseInt(request.getParameter("delete")));
                }
                if (request.getParameter("delete") == null) {
                    if (user.getRolesID() == 1) {
                        if (!request.getParameter("Login").equals(userNew.getEmail())) {
                            userNew.setLogin(FormUtil.getString(request.getParameter("Login"),
                                    "[A-Za-z0-9_@.]+"));
                        }
                    }
                    if (!request.getParameter("Email").equals(userNew.getEmail())) {
                        userNew.setEmail(FormUtil.getString(request.getParameter("Email"),
                                "([A-Za-z0-9_]*)[@a-z0-9_\\\\.-]+"));
                    }
                    if (request.getParameter("Password") != "") {
                        userNew.setPassword(FormUtil.getString(request.getParameter("Password"),
                                "[A-Za-z0-9_А-Яа-яЁё]+"));
                    }
                    if (request.getParameter("City") != "") {
                        userNew.setCityID(Integer.parseInt(request.getParameter("City")));
                    }
                    if (!request.getParameter("address").equals(userNew.getAddress())) {
                        userNew.setAddress(FormUtil.getString(request.getParameter("address"),
                                "[A-Za-z0-9_А-Яа-яЁё., -]+"));

                    }
                    if (!request.getParameter("phoneNumber").equals(userNew.getPhoneNumber())) {
                        userNew.setPhoneNumber(FormUtil.getString(request.getParameter("phoneNumber"),
                                "[0-9+]*"));

                    }
                }
            } catch (ParseException e) {
                request.setAttribute(Message.MESSAGE, "Введены недопустимые символы");
                return null;
            }
            if (request.getParameter("delete") != null && user.getRolesID() == 1) {
                DAO.getDAO().user.delete(userNew);
                request.setAttribute(Message.MESSAGE, "Пользователь удалён");
            } else {
                DAO.getDAO().user.update(userNew);
                if (request.getParameter(request.getParameter("button")) != null) {
                    user = userNew;
                }
                request.setAttribute(Message.MESSAGE, "Данные изменены");
            }
            CookiesUser.setCookie(response, user);
            if (user.getRolesID() == 1) {
                List<User> users = DAO.getDAO().user.getAll();
                request.setAttribute("users", users);
            }
        }
        return null;
    }
}
