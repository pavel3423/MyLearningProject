package project.java.entity;

import java.io.Serializable;
import java.util.Objects;

public class CarClass implements Serializable {
    private int id;
    private String carClass;

    public CarClass(int id, String carClass) {
        this.id = id;
        this.carClass = carClass;
    }

    public CarClass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    @Override
    public String toString() {
        return "CarClass{" +
                "id=" + id +
                ", carClass='" + carClass + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarClass carClass1 = (CarClass) o;
        return id == carClass1.id &&
                Objects.equals(carClass, carClass1.carClass);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, carClass);
    }
}
