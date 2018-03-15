package project.java.entity;

import java.io.Serializable;
import java.util.Objects;

public class Body implements Serializable {
    private int id;
    private String body;

    public Body(int id, String body) {
        this.id = id;
        this.body = body;
    }

    public Body() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Body{" +
                "id=" + id +
                ", body='" + body + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body body1 = (Body) o;
        return id == body1.id &&
                Objects.equals(body, body1.body);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, body);
    }
}
