package projectTest;

import org.junit.Assert;
import org.junit.Test;
import project.java.dao.dao.DAO;
import project.java.entity.Role;

import java.sql.SQLException;

public class RoleDAOTest {
    DAO dao = DAO.getDAO();
    Role role;
    String test;

    @Test
    public void readRole() throws SQLException {
        role = dao.role.read(1);
        test = "Role{id=1, role='Администратор'}";
        Assert.assertEquals("Error read", test, role.toString());
    }

    @Test
    public void createRole() throws SQLException {
        Assert.assertFalse("Error create", dao.role.create(role));
    }

    @Test
    public void updateRole() throws SQLException {
        Assert.assertFalse("Error update", dao.role.update(role));
    }

    @Test
    public void deleteRole() throws SQLException {
        Assert.assertFalse("Error delete", dao.role.delete(role));
    }

    @Test
    public void getAllRole() throws SQLException {
        Assert.assertNotNull("Error getAll", dao.role.getAll().get(0));
    }
}
