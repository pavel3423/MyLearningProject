package project.java.dao.dao;

import project.java.dao.connect.ConnectionCreator;
import project.java.entity.City;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements IDAO<City> {
    @Override
    public boolean create(City bean) throws SQLException {
        return false;
    }

    @Override
    public City read(int id) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`city` WHERE id=%d",
                    id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new City(
                        resultSet.getInt("id"),
                        resultSet.getString("city")
                );
            }
        }
        return null;
    }

    public City read(String city) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`city` WHERE city='%s'",
                    city);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new City(
                        resultSet.getInt("id"),
                        resultSet.getString("city")
                );
            }
        }
        return null;
    }

    @Override
    public boolean update(City bean) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(City bean) throws SQLException {
        return false;
    }

    @Override
    public List<City> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<City> getAll(String WHERE) throws SQLException {
        List<City> citys = new ArrayList<>();
        String sql = String.format("SELECT * FROM `kozlov`.`city` " + WHERE + ";");
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                City city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("city")
                );
                citys.add(city);
            }
        }
        return citys;
    }
}
