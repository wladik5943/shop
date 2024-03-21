package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.ShopEntity;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.UserRole;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class UserRepository implements by.teachmeskills.shop.repository.interfaces.UserRepository {

    private final String ADD_USER = "insert into shop.users (id, name,surname,login,password,role) " +
            "values(?,?,?,?,?,?)";

    private final String MAX_ID = "select max(id) from shop.users";
    private final String LOGINSEARCH = "select * from shop.users where login = ?";
    private final String IDSEARCH = "select * from shop.users where id = ?";
    private final String UPDATE = "update shop.users set name= ? , surname = ? , login = ? , password = ? , role = ? where id = ?";
    @Override
    public void updateUser(User user) {
//        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
//        User[] arr = new User[users.size()];
//        users.toArray(arr);
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i].getId() == user.getId()) {
//                arr[i] = user;
//                break;
//            }
//        }
//        users.clear();
//        Arrays.stream(arr).forEach(x -> {
//            users.add(x);
//        });
//        SerializeUser.serialize(users, "D:\\progects\\shop\\src\\main\\resources\\res");

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getLogin());
            preparedStatement.setString(4,user.getPassword());
            if(user.getRole() == UserRole.ADMIN)
                preparedStatement.setString(5,"ADMIN");
            else
                preparedStatement.setString(5,"CLIENT");
            preparedStatement.setInt(6,user.getId());
            preparedStatement.execute();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e);
        }

    }


    public User idSearch(int id) {
//        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
//        for (User temp : users)
//            if (temp.getId() == id)
//                return temp;
//        return null;
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        User user = new User();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(IDSEARCH);
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
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return user;
    }



    public User loginSearch(String login) {
//        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
//        for (User temp : users)
//            if (temp.getLogin().equals(login))
//                return temp;
//        return null;

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        User user = new User();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(LOGINSEARCH);
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    @Override
    public void add(User user) {
//        User user = (User)ent;
//        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
//        user.setId(users.size() + 1);
//        users.add(user);
//        SerializeUser.serialize(users,"D:\\progects\\shop\\src\\main\\resources\\res");
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatementMax = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMax.executeQuery();
            resultSet.next();
            var maxId = resultSet.getInt(1);
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void deleteById(int userId) {
        Collection<User> users = SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");
        for (User temp : users) {
            if (temp.getId() == userId) {
                users.remove(temp);
                break;
            }
        }
        SerializeUser.serialize(users, "D:\\progects\\shop\\src\\main\\resources\\res");
    }

@Override
    public Collection<User> all() {
//   return SerializeUser.deSerialize("D:\\progects\\shop\\src\\main\\resources\\res");

        Collection<User> users = new LinkedList<>();
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from shop.users");
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return users;
        }

    }
}
