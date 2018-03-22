package project.java.controller;

import project.java.dao.dao.DAO;
import project.java.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CommandAllCarsUser extends Action {
    @Override
    Action execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        if (user == null) {
            session.setAttribute(Message.MESSAGE, "Войдите чтобы просмотреть объявления");
            return Actions.LOGIN.command;
        }
        List<Car> cars = null;
        if (user.getRolesID() != 1) {
            cars = DAO.getDAO().car.getAll(String.format("WHERE usersID=%d", user.getId()));
        } else if (user.getRolesID() == 1) {
            cars = DAO.getDAO().car.getAll();
        }
        request.setAttribute("cars", cars);
        List<Brand> brands = DAO.getDAO().brand.getAll();
        request.setAttribute("brands", brands);
        List<CarClass> carClasses = DAO.getDAO().carClass.getAll();
        request.setAttribute("carClasses", carClasses);
        List<Year> years = DAO.getDAO().year.getAll();
        request.setAttribute("years", years);
        if (FormUtil.isPost(request)) {
            Car car;
            if (request.getParameter("Delete") != null) {
                car = new Car();
                car.setId(parseInt(request.getParameter("Delete")));
                DAO.getDAO().car.delete(car);
                request.setAttribute(Message.MESSAGE, "Автомобиль удалён");
                cars = DAO.getDAO().car.getAll(String.format("WHERE usersID=%d", user.getId()));
                request.setAttribute("cars", cars);
                return null;
            } else {
                try {
                    if (request.getParameter("Price") != "") {
                        car = Car.getCar(user, request);
                    } else {
                        request.setAttribute(Message.MESSAGE, "Введите цену");
                        return null;
                    }
                } catch (ParseException e) {
                    request.setAttribute(Message.MESSAGE, "Введены недопустимые символы");
                    return null;
                }
                car.setId(parseInt(request.getParameter("Submit")));
                DAO.getDAO().car.update(car);
                request.setAttribute(Message.MESSAGE, "Данные изменены");
                if (user.getRolesID() != 1) {
                    cars = DAO.getDAO().car.getAll(String.format("WHERE usersID=%d", user.getId()));
                } else if (user.getRolesID() == 1) {
                    cars = DAO.getDAO().car.getAll();
                }
                request.setAttribute("cars", cars);
                return null;
            }
        } else {
            return null;
        }
    }
}

