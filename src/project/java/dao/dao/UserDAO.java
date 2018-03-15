package project.java.dao.dao;

import project.java.dao.connect.ConnectionCreator;
import project.java.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IDAO<User> {


    @Override
    public boolean create(User user) throws SQLException {
        user.setId(0);
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO `kozlov`.`users` (`login`, `email`, `password`, `cityID`, `address`, `phoneNumber`, `rolesID`) VALUES ('%s', '%s', '%s', %d, '%s', '%s', %d);",
                    user.getLogin(), user.getEmail(), user.getPassword(), user.getCityID(), user.getAddress(), user.getPhoneNumber(), user.getRolesID());
            int count = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (count == 1) {
                ResultSet key = statement.getGeneratedKeys();
                if (key.next()) {
                    user.setId(key.getInt(1));
                }
            }
            return count == 1;
        }
    }

    @Override
    public User read(int id) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`users` WHERE id=%d",
                    id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("cityID"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getInt("rolesID")
                );
            }
        }
        return null;
    }

    @Override
    public boolean update(User user) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("UPDATE `kozlov`.`users` SET `login`='%s',`email`='%s',`password`='%s',`cityID`='%d',`address`='%s',`phoneNumber`='%s',`rolesID`='%d' WHERE id=%d",
                    user.getLogin(), user.getEmail(), user.getPassword(), user.getCityID(), user.getAddress(), user.getPhoneNumber(), user.getRolesID(), user.getId());
            int count = statement.executeUpdate(sql);
            return count == 1;
        }
    }

    @Override
    public boolean delete(User user) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("DELETE FROM `kozlov`.`users` WHERE id=%d",
                    user.getId());
            int count = statement.executeUpdate(sql);
            return count == 1;
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<User> getAll(String WHERE) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM `kozlov`.`users` " + WHERE + ";";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("cityID"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getInt("rolesID")
                );
                users.add(user);
            }
        }
        return users;
    }
}
