package project.java.dao.dao;

import project.java.entity.Body;

import java.util.ArrayList;
import java.util.List;

public class BodyDAO {
    private static List<Body> bodies = new ArrayList<>();

    public List<Body> getAll() {
        if (this.bodies.size() > 0) {
            return this.bodies;
        } else {
            synchronized (this) {
                if (this.bodies.size() > 0) {
                    return this.bodies;
                } else {
                    bodies.add(new Body(1, "Хэтчбек"));
                    bodies.add(new Body(2, "Седан"));
                    bodies.add(new Body(3, "Кроссовер"));
                    bodies.add(new Body(4, "Внедорожник"));
                    return this.bodies;
                }
            }
        }
    }
}
