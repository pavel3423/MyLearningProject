package project.java.dao.dao;

import project.java.dao.connect.ConnectionCreator;
import project.java.entity.Car;
import project.java.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Init {
    public static void resetDB() throws SQLException {

        try (Connection connection = ConnectionCreator.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP SCHEMA IF EXISTS `kozlov` ;");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `kozlov` DEFAULT CHARACTER SET utf8 ;");
            statement.executeUpdate("USE `kozlov` ;");
            statement.executeUpdate("DROP TABLE IF EXISTS `kozlov`.`brand` ;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kozlov`.`brand` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `brand` VARCHAR(50) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "AUTO_INCREMENT = 1\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.executeUpdate("DROP TABLE IF EXISTS `kozlov`.`city` ;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kozlov`.`city` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `city` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "AUTO_INCREMENT = 1\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.executeUpdate("DROP TABLE IF EXISTS `kozlov`.`roles` ;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kozlov`.`roles` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `role` VARCHAR(50) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "AUTO_INCREMENT = 1\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.executeUpdate("DROP TABLE IF EXISTS `kozlov`.`users` ;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kozlov`.`users` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `login` VARCHAR(50) NOT NULL,\n" +
                    "  `email` VARCHAR(50) NOT NULL,\n" +
                    "  `password` VARCHAR(50) NOT NULL,\n" +
                    "  `cityID` INT(11) NOT NULL,\n" +
                    "  `address` VARCHAR(100) NOT NULL,\n" +
                    "  `phoneNumber` VARCHAR(50) NOT NULL,\n" +
                    "  `rolesID` INT(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`ID`),\n" +
                    "  UNIQUE INDEX `idx_users_login` (`login` ASC)," +
                    "  INDEX `fk_users_roles_idx` (`rolesID` ASC),\n" +
                    "  INDEX `fk_users_city1_idx` (`cityID` ASC),\n" +
                    "  CONSTRAINT `fk_users_city1`\n" +
                    "    FOREIGN KEY (`cityID`)\n" +
                    "    REFERENCES `kozlov`.`city` (`id`),\n" +
                    "  CONSTRAINT `fk_users_roles`\n" +
                    "    FOREIGN KEY (`rolesID`)\n" +
                    "    REFERENCES `kozlov`.`roles` (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;\n");
            statement.executeUpdate("DROP TABLE IF EXISTS `kozlov`.`cars` ;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `kozlov`.`cars` (\n" +
                    "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `brandID` INT(11) NOT NULL,\n" +
                    "  `model` VARCHAR(50) NOT NULL,\n" +
                    "  `carClass` INT(11) NOT NULL,\n" +
                    "  `price` DOUBLE NOT NULL,\n" +
                    "  `year` INT(11) NOT NULL,\n" +
                    "  `usersID` INT(11) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  INDEX `fk_cars_users1_idx` (`usersID` ASC),\n" +
                    "  INDEX `fk_cars_brand1_idx` (`brandID` ASC),\n" +
                    "  CONSTRAINT `fk_cars_brand1`\n" +
                    "    FOREIGN KEY (`brandID`)\n" +
                    "    REFERENCES `kozlov`.`brand` (`id`),\n" +
                    "  CONSTRAINT `fk_cars_users1`\n" +
                    "    FOREIGN KEY (`usersID`)\n" +
                    "    REFERENCES `kozlov`.`users` (`id`)\n" +
                    "    ON DELETE CASCADE\n" +
                    "    ON UPDATE CASCADE)\n" +
                    "ENGINE = InnoDB\n" +
                    "AUTO_INCREMENT = 1\n" +
                    "DEFAULT CHARACTER SET = utf8;");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('Audi'); ");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('BMW'); ");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('Lexus'); ");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('Mercedes-Benz');");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('Porsche'); ");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('Tesla'); ");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('Volkswagen'); ");
            statement.executeUpdate("INSERT INTO `kozlov`.`brand` (`brand`) VALUES ('Volvo'); ");

            statement.executeUpdate("INSERT INTO `kozlov`.`city` (`city`) VALUES ('Брестская область');");
            statement.executeUpdate("INSERT INTO `kozlov`.`city` (`city`) VALUES ('Витебская область');");
            statement.executeUpdate("INSERT INTO `kozlov`.`city` (`city`) VALUES ('Гомельская область');");
            statement.executeUpdate("INSERT INTO `kozlov`.`city` (`city`) VALUES ('Гродненская область');");
            statement.executeUpdate("INSERT INTO `kozlov`.`city` (`city`) VALUES ('Минская область');");
            statement.executeUpdate("INSERT INTO `kozlov`.`city` (`city`) VALUES ('Могилевская область');");
            statement.executeUpdate("INSERT INTO `kozlov`.`city` (`city`) VALUES ('г. Минск');");

            statement.executeUpdate("INSERT INTO `kozlov`.`roles` (`role`) VALUES ('Администратор');");
            statement.executeUpdate("INSERT INTO `kozlov`.`roles` (`role`) VALUES ('Пользователь');");

            DAO.getDAO().user.create(new User(0, "admin", "admin@admin.by", "admin", 7, "ул. Пушкина", "+375299988777", 1));
            DAO.getDAO().user.create(new User(0, "bayernkraft.by", "bmw.service@bayernkraft.by", "bayernkraft", 7, "ул. Панченко, 9", "+375447730077", 2));
            DAO.getDAO().user.create(new User(0, "mercedes-benz.by", "info@mercedes-benz.by", "mercedes", 7, "ул. Тимирязева, 70", "+375296039999", 2));

            DAO.getDAO().car.create(new Car(0, 2, "7 series", 2, 164400.0, 2018, 2));
            DAO.getDAO().car.create(new Car(0, 2, "X6", 3, 132000.0, 2018, 2));
            DAO.getDAO().car.create(new Car(0, 4, "E 200 4MATIC", 2, 137706.0, 2018, 3));
            DAO.getDAO().car.create(new Car(0, 4, "GLS", 4, 164700.0, 2018, 3));
        }
    }

    public static void main(String[] args) {
        try {
            Init.resetDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}