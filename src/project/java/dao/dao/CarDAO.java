package project.java.dao.dao;

import project.java.dao.connect.ConnectionCreator;
import project.java.entity.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CarDAO implements IDAO<Car> {


    @Override
    public boolean create(Car car) throws SQLException {
        car.setId(0);
        Connection connection = ConnectionCreator.getConnection();
        Statement statement = connection.createStatement();
        String sql = String.format(new Locale("en", "US"), "INSERT INTO `kozlov`.`cars` (`brandID`, `model`, `carClass`, `price`, `year`, `usersID`) VALUES ('%d', '%s', '%s', '%G', '%d', '%d');",
                car.getBrandID(), car.getModel(), car.getCarClass(), car.getPrice(), car.getYear(), car.getUsersID());
        int count = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        if (count == 1) {
            ResultSet key = statement.getGeneratedKeys();
            if (key.next()) {
                car.setId(key.getInt(1));
            }
        }
        return count == 1;
    }

    @Override
    public Car read(int id) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("SELECT * FROM `kozlov`.`cars` WHERE id=%d",
                    id);
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return new Car(
                        resultSet.getInt("id"),
                        resultSet.getInt("brandID"),
                        resultSet.getString("model"),
                        resultSet.getInt("carClass"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("year"),
                        resultSet.getInt("usersID")
                );
            }
        }
        return null;
    }

    @Override
    public boolean update(Car car) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format(new Locale("en", "US"), "UPDATE `kozlov`.`cars` SET `brandID`='%d',`model`='%s',`carClass`='%s',`price`='%G',`year`='%d',`usersID`='%d' WHERE id=%d",
                    car.getBrandID(), car.getModel(), car.getCarClass(), car.getPrice(), car.getYear(), car.getUsersID(), car.getId());
            int count = statement.executeUpdate(sql);
            return count == 1;
        }
    }

    @Override
    public boolean delete(Car car) throws SQLException {
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = String.format("DELETE FROM `kozlov`.`cars` WHERE id=%d",
                    car.getId());
            int count = statement.executeUpdate(sql);
            return count == 1;
        }
    }

    @Override
    public List<Car> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Car> getAll(String WHERE) throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM `kozlov`.`cars` " + WHERE + ";";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getInt("id"),
                        resultSet.getInt("brandID"),
                        resultSet.getString("model"),
                        resultSet.getInt("carClass"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("year"),
                        resultSet.getInt("usersID")
                );
                cars.add(car);
            }
        }
        return cars;
    }
}
