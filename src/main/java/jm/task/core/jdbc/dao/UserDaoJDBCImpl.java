//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//
//    public UserDaoJDBCImpl() {}
//
//    Connection connection = Util.getConnection();
//
//    @Override
//    public void createUsersTable() {
//        String str = "CREATE TABLE IF NOT EXISTS`db_mysql`.`users` (\n" +
//                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
//                "  `name` VARCHAR(45) NOT NULL,\n" +
//                "  `lastName` VARCHAR(45) NOT NULL,\n" +
//                "  `age` INT(3) NOT NULL,\n" +
//                "  PRIMARY KEY (`id`),\n" +
//                "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(str);
//            System.out.println("The table 'user' was create successfully");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void dropUsersTable() {
//        String str = "DROP TABLE IF EXISTS users";
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(str);
//            System.out.println("The table 'user' was delete");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        String str = "INSERT INTO users (name, lastName, age) VALUE (?, ?, ?)";
//        try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();
//            System.out.println( "User " + name + " " + lastName + " " + age + " was added");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void removeUserById(long id) {
//        String str = "DELETE FROM users WHERE id = ?";
//        try(PreparedStatement preparedStatement = connection.prepareStatement(str);
//            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM users")){
//
//            while (resultSet.next()) {
//                User user = new User();
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        String str = "SELECT * FROM users";
//        List<User> result = new ArrayList<>();
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(str)) {
//
//            while (resultSet.next()){
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("LastName"));
//                user.setAge(resultSet.getByte("id"));
//
//                result.add(user);
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public void cleanUsersTable() {
//                String str = "TRUNCATE users";
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(str);
//            System.out.println("All 'user' was delete");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
