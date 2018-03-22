package project.java.controller;

import project.java.dao.dao.DAO;
import project.java.entity.CarClass;
import project.java.entity.Brand;
import project.java.entity.Car;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class CommandIndex extends Action {
    @Override
    public Action execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<Car> cars = DAO.getDAO().car.getAll();
        request.setAttribute("cars", cars);
        List<Brand> brands = DAO.getDAO().brand.getAll();
        request.setAttribute("brands", brands);
        List<CarClass> carClasses = DAO.getDAO().carClass.getAll();
        request.setAttribute("carClasses", carClasses);
        return null;
    }
}
