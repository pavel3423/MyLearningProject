package project.java.controller;

import project.java.dao.dao.DAO;
import project.java.entity.*;

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

public class CommandAddCar extends Action {
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute(Message.MESSAGE, "Войдите чтобы добавить автомобиль");
            return Actions.LOGIN.command;
        }
        List<Body> bodies = DAO.getDAO().body.getAll();
        request.setAttribute("bodies", bodies);
        List<Year> years = DAO.getDAO().year.getAll();
        request.setAttribute("years", years);
        List<Brand> brands = DAO.getDAO().brand.getAll();
        request.setAttribute("brands", brands);
        if (FormUtil.isPost(request)) {
            Car car;
            try {
                car = Car.getCar(user, request);
            } catch (ParseException e) {
                request.setAttribute(Message.MESSAGE, "Введены недопустимые символы");
                return null;
            }
            DAO.getDAO().car.create(car);
            request.setAttribute(Message.MESSAGE, "Автомобиль добавлен");
            return null;
        } else {
            return null;
        }
    }
}
