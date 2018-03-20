package projectTest;

import org.junit.Assert;
import org.junit.Test;
import project.java.dao.dao.DAO;
import project.java.entity.User;

import java.sql.SQLException;

public class UserDAOTest {
    static User user;
    static DAO dao = DAO.getDAO();
    String test;

    @Test
    public void readUser() throws SQLException {
        user = dao.user.read(1);
        test = "User{id=1, login='admin', email='admin@admin.by', password='qwerty', cityID=7, address='ул. Пушкина', phoneNumber='+375299988777', rilesID=1}";
        Assert.assertEquals("Error read", test, user.toString());
    }

    @Test
    public void createUser() throws SQLException {
        user.setPassword("12345678");
        user.setLogin("adminTest");
        dao.user.create(user);
        user = dao.user.read(user.getId());
        test = String.format("User{id=%d, login='adminTest', email='admin@admin.by', password='12345678', cityID=7, address='ул. Пушкина', phoneNumber='+375299988777', rilesID=1}", user.getId());
        Assert.assertEquals("Error create", test, user.toString());
    }

    @Test
    public void updateUser() throws SQLException {
        user.setPassword("qwerty");
        dao.user.update(user);
        user = dao.user.read(user.getId());
        test = String.format("User{id=%d, login='admin', email='admin@admin.by', password='qwerty', cityID=7, address='ул. Пушкина', phoneNumber='+375299988777', rilesID=1}", user.getId());
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
