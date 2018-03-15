package project.java.dao.dao;

import project.java.dao.connect.ConnectionCreator;
import project.java.entity.Brand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO implements IDAO<Brand> {
    @Override
    public boolean create(Brand bean) throws SQLException {
        return false;
    }

    @Override
    public Brand read(int id) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`brand` WHERE id=%d",
                    id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Brand(
                        resultSet.getInt("id"),
                        resultSet.getString("brand")
                );
            }
        }
        return null;
    }

    public Brand read(String brand) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`brand` WHERE brand='%s'",
                    brand);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Brand(
                        resultSet.getInt("id"),
                        resultSet.getString("brand")
                );
            }
        }
        return null;
    }

    @Override
    public boolean update(Brand bean) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Brand bean) throws SQLException {
        return false;
    }

    @Override
    public List<Brand> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Brand> getAll(String WHERE) throws SQLException {
        List<Brand> brands = new ArrayList<>();
        String sql = String.format("SELECT * FROM `kozlov`.`brand` " + WHERE + ";");
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Brand brand = new Brand(
                        resultSet.getInt("id"),
                        resultSet.getString("brand")
                );
                brands.add(brand);
            }
        }
        return brands;
    }
}
