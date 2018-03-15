package project.java.dao.dao;

import project.java.dao.connect.ConnectionCreator;
import project.java.entity.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements IDAO<Role> {
    @Override
    public boolean create(Role role) throws SQLException {
        return false;
    }

    @Override
    public Role read(int id) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`roles` WHERE id=%d",
                    id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("role")
                );
            }
        }
        return null;
    }

    public Role read(String role) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`roles` WHERE role='%s'",
                    role);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("role")
                );
            }
        }
        return null;
    }

    @Override
    public boolean update(Role role) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Role role) throws SQLException {
        return false;
    }

    @Override
    public List<Role> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Role> getAll(String WHERE) throws SQLException {
        List<Role> roles = new ArrayList<>();
        String sql = String.format("SELECT * FROM `kozlov`.`roles` " + WHERE + ";");
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("role")
                );
                roles.add(role);
            }
        }
        return roles;
    }
}
