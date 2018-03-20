package projectTest;

import org.junit.Assert;
import org.junit.Test;
import project.java.dao.dao.DAO;
import project.java.entity.User;

import java.sql.SQLException;

public class UserDAOTest {
    User user;
    DAO dao = DAO.getDAO();

    @Test
    public void readUser() throws SQLException {
        this.user = dao.user.read(1);
        String test = "User{id=1, login='bayernkraft.by', email='bmw.service@bayernkraft.by', password='bayernkraft', cityID=7, address='ул. Панченко, 9', phoneNumber='+375447730077', rilesID=2}";
        Assert.assertEquals("Error read", test, user.toString());
    }

    @Test
    public void createUser() throws SQLException {
        user.setPassword("12345678");
        dao.user.create(user);
        user = dao.user.read(user.getId());
        String test = String.format("User{id=%d, login='bayernkraft.by', email='bmw.service@bayernkraft.by', password='12345678', cityID=7, address='ул. Панченко, 9', phoneNumber='+375447730077', rilesID=2}", user.getId());
        Assert.assertEquals("Error create", test, user.toString());
    }

    @Test
    public void updateUser() throws SQLException {
        user.setPassword("qwerty");
        dao.user.update(user);
        user = dao.user.read(user.getId());
        String test = String.format("User{id=%d, login='bayernkraft.by', email='bmw.service@bayernkraft.by', password='qwerty', cityID=7, address='ул. Панченко, 9', phoneNumber='+375447730077', rilesID=2}", user.getId());
        Assert.assertEquals("Error update", test, user.toString());
    }

    @Test
    public void deleteUser() throws SQLException {
        int id = user.getId();
        dao.user.delete(user);
        user = dao.user.read(id);
        Assert.assertNull("Error delete", user);
    }

    @Test
    public void getAllUser() throws SQLException {
        Assert.assertNotNull("Error getAll", dao.user.getAll().get(0));
    }
}
