package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.enums.GoodSubtupe;
import by.teachmeskills.shop.enums.OrderStatus;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class OrderRepository {
    private final String SEARCH_BY_ID_NOTPROCESSED = "select * from shop.order where userid = ? and status = 'NOTPROCESSED'";
    private final String MAX_ID = "select max(id) from shop.order";
    private final String ADD_ORDER = "insert into shop.order (id,userid,status)" +
            "values(?,?,?)";
    private final String UPDATE_ORDER_STATUS = "update shop.order set status= ? where id = ?";
    private final String PROCESSED_ORDERS = "select * from shop.order where status = 'PROCESSED'";
    private final String SEARCH_BY_ID = "select * from shop.order where id = ?";
    private final String ALL_ORDERS_EXCEPT_NOTPROCESSED_BY_ID = "select * from shop.order where userid = ? and status != 'NOTPROCESSED'";
    private final String DELETE_ORDER = "delete from shop.order where id = ?";
    public void deleteOrder(int orderId){
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Order> allOrdersExceptNOTPROCESSEDByUserId(int userId){
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        Collection<Order> orders = new LinkedList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(ALL_ORDERS_EXCEPT_NOTPROCESSED_BY_ID);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setStatus(statusFromDatabase(resultSet));
                order.setUserId(userId);
                orders.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public void changeStatusForPACKED(int orderId) {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
            preparedStatement.setString(1,"PACKED");
            preparedStatement.setInt(2, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Order searchById(int orderId) {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        Order order = new Order();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_ID);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order.setId(resultSet.getInt("id"));
            order.setUserId(resultSet.getInt("userid"));
            order.setStatus(statusFromDatabase(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public Collection<Order> ProcessedOrders() {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        Collection<Order> orders = new LinkedList<>();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(PROCESSED_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("userid"));
                order.setStatus(OrderStatus.PROCESSED);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public void changeStatusForPROCESSED(int orderId) {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        Order order = new Order();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
            preparedStatement.setString(1,"PROCESSED");
            preparedStatement.setInt(2, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Order searchbyUserIdNotProcessed(int userid, boolean create) {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        Order order = new Order();
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_ID_NOTPROCESSED);
            preparedStatement.setInt(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            if (!result && create) {
                order = createOrder(userid);
            } else if (!result) {
                return null;
            } else {
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

    private Order createOrder(int userId) {
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
            order.setId(++maxId);
            order.setUserId(userId);
            order.setStatus(OrderStatus.NOTPROCESSED);
            PreparedStatement preparedStatementAdd = connection.prepareStatement(ADD_ORDER);
            preparedStatementAdd.setInt(1, order.getId());
            preparedStatementAdd.setInt(2, order.getUserId());
            preparedStatementAdd.setString(3, "NOTPROCESSED");
            preparedStatementAdd.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    private OrderStatus statusFromDatabase(ResultSet resultSet) {
        try {
            switch (resultSet.getString("status")) {
                case "PROCESSED" -> {
                    return OrderStatus.PROCESSED;
                }
                case "NOTPROCESSED" -> {
                    return OrderStatus.NOTPROCESSED;
                }
                case "PACKED" -> {
                    return OrderStatus.PACKED;
                }
                case "DELIVERED" -> {
                    return OrderStatus.DELIVERED;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
