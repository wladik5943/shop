package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.enums.OrderStatus;

import java.sql.*;

public class OrderRepository {
    private final String SEARCH_BY_ID_NOTPROCESSED = "select * from shop.order where userid = ? and status = 'NOTPROCESSED'";
    private final String MAX_ID = "select max(id) from shop.order";
    private final String ADD_ORDER = "insert into shop.order (id,userid,status)" +
            "values(?,?,?)";
    public Order searchbyUserIdNotProcessed(int userid) {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        Order order = new Order();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_ID_NOTPROCESSED);
            preparedStatement.setInt(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                PreparedStatement preparedStatementMax = connection.prepareStatement(MAX_ID);
                ResultSet resultSetMax = preparedStatementMax.executeQuery();
                resultSetMax.next();
                var maxId = resultSetMax.getInt(1);
                order.setId(++maxId);
                order.setUserId(userid);
                order.setStatus(OrderStatus.NOTPROCESSED);
                PreparedStatement preparedStatementAdd = connection.prepareStatement(ADD_ORDER);
                preparedStatementAdd.setInt(1,order.getId());
                preparedStatementAdd.setInt(2,order.getUserId());
                preparedStatementAdd.setString(3,"NOTPROCESSED");
                preparedStatementAdd.execute();
            }
            else{
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userid"));
                order.setStatus(OrderStatus.NOTPROCESSED);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
