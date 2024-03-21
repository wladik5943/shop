package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.Basket;
import by.teachmeskills.shop.entity.Order;

import java.sql.*;

public class BasketRepository {

    private final String MAX_ID = "select max(id) from shop.basket";
    private final String ADD_BASKET = "insert into shop.basket (id,orderid,goodid,count)" +
            "values(?,?,?,?)";

    public void add(Basket basket){
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        Order order = new Order();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatementMax = connection.prepareStatement(MAX_ID);
            ResultSet resultSetMax = preparedStatementMax.executeQuery();
            resultSetMax.next();
            var maxId = resultSetMax.getInt(1);

            basket.setId(++maxId);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BASKET);
            preparedStatement.setInt(1,basket.getId());
            preparedStatement.setInt(2,basket.getOrderId());
            preparedStatement.setInt(3,basket.getGoodId());
            preparedStatement.setInt(4,basket.getCount());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
