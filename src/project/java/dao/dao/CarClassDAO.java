package project.java.dao.dao;

import project.java.entity.CarClass;

import java.util.ArrayList;
import java.util.List;

public class CarClassDAO {
    private static List<CarClass> carClass = new ArrayList<>();

    public List<CarClass> getAll() {
        if (this.carClass.size() > 0) {
            return this.carClass;
        } else {
            synchronized (this) {
                if (this.carClass.size() > 0) {
                    return this.carClass;
                } else {
                    carClass.add(new CarClass(1, "Хэтчбек"));
                    carClass.add(new CarClass(2, "Седан"));
                    carClass.add(new CarClass(3, "Кроссовер"));
                    carClass.add(new CarClass(4, "Внедорожник"));
                    return this.carClass;
                }
            }
        }
    }
}
