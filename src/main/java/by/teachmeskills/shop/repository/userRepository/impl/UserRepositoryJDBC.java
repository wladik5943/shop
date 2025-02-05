package by.teachmeskills.shop.repository.userRepository.impl;

import by.teachmeskills.shop.config.Connection.JDBCConnection;
import by.teachmeskills.shop.config.SQL.SQLRequestForUser;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.UserRole;
import by.teachmeskills.shop.repository.userRepository.UserRepository;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserRepositoryJDBC implements UserRepository {

    private final SQLRequestForUser sqlRequest = new SQLRequestForUser();

    @Override
    public void updateUser(User user) {

        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest.getUPDATE());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            if (user.getRole() == UserRole.ADMIN)
                preparedStatement.setString(5, "ADMIN");
            else
                preparedStatement.setString(5, "CLIENT");
            preparedStatement.setInt(6, user.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public User idSearch(int id) {
        Connection connection = JDBCConnection.getConnection();
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest.getIDSEARCH());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setLogin(resultSet.getString("login"));
            if (resultSet.getString("role").equals("CLIENT"))
                user.setRole(UserRole.CLIENT);
            else
                user.setRole(UserRole.ADMIN);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    public User loginSearch(String login) {
        Connection connection = JDBCConnection.getConnection();
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest.getLOGINSEARCH());
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setLogin(login);
            if (resultSet.getString("role").equals("CLIENT"))
                user.setRole(UserRole.CLIENT);
            else
                user.setRole(UserRole.ADMIN);

        } catch (SQLException e) {
            return null;
        }
        return user;
    }


    @Override
    public void add(User user) {
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatementMax = connection.prepareStatement(sqlRequest.getMAX_ID());
            ResultSet resultSet = preparedStatementMax.executeQuery();
            resultSet.next();
            var maxId = resultSet.getInt(1);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest.getADD_USER());
            preparedStatement.setInt(1, ++maxId);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            String role;
            if (user.getRole() == UserRole.ADMIN)
                role = "ADMIN";
            else
                role = "CLIENT";
            preparedStatement.setString(6, role);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteById(int userId) {

    }

    @Override
    public Collection<User> all() {
        Collection<User> users = new LinkedList<>();
        Connection connection = JDBCConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlRequest.getALL());
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                if (resultSet.getString("role").equals("CLIENT"))
                    user.setRole(UserRole.CLIENT);
                else
                    user.setRole(UserRole.ADMIN);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}

