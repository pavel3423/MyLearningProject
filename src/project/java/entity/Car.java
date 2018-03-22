package project.java.entity;

import project.java.controller.FormUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class Car implements Serializable {
    private int id;
    private int brandID;
    private String model;
    private int carClass;
    private Double price;
    private int year;
    private int usersID;

    public Car(int id, int brandID, String model, int carClass, Double price, int year, int usersID) {
        this.id = id;
        this.brandID = brandID;
        this.model = model;
        this.carClass = carClass;
        this.price = price;
        this.year = year;
        this.usersID = usersID;
    }

    public static Car getCar(User user, HttpServletRequest request) throws ParseException {
        return new Car(0,
                Integer.parseInt(request.getParameter("Brand")),
                FormUtil.getString(request.getParameter("Model"), "[A-Za-z0-9_А-Яа-яЁё ]+"),
                Integer.parseInt(request.getParameter("CarClass")),
                Double.parseDouble(request.getParameter("Price").replace(",", ".")),
                Integer.parseInt(request.getParameter("Year")),
                user.getId());

    }


    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCarClass() {
        return carClass;
    }

    public void setCarClass(int carClass) {
        this.carClass = carClass;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getUsersID() {
        return usersID;
    }

    public void setUsersID(int usersID) {
        this.usersID = usersID;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brandID=" + brandID +
                ", model='" + model + '\'' +
                ", carClass='" + carClass + '\'' +
                ", price=" + price +
                ", year=" + year +
                ", usersID=" + usersID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                brandID == car.brandID &&
                year == car.year &&
                usersID == car.usersID &&
                Objects.equals(model, car.model) &&
                Objects.equals(carClass, car.carClass) &&
                Objects.equals(price, car.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandID, model, carClass, price, year, usersID);
    }
}
