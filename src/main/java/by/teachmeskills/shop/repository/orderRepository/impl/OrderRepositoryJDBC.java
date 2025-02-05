package by.teachmeskills.shop.repository.orderRepository.impl;

import by.teachmeskills.shop.config.Connection.JDBCConnection;
import by.teachmeskills.shop.config.SQL.SQLRequestForOrder;
import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.enums.OrderStatus;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class OrderRepositoryJDBC {


    private final SQLRequestForOrder sql = new SQLRequestForOrder();

    public void updateCost(int orderId, int cost){
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getUPDATE_COST_BY_ORDER_ID());
            preparedStatement.setInt(1,cost);
            preparedStatement.setInt(2, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int orderCost(int orderId){
        Connection connection = JDBCConnection.getConnection();
        int cost;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getCOST_BY_ORDER_ID());
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cost = resultSet.getInt("cost");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cost;
    }

    public void deleteOrder(int orderId){
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getDELETE_ORDER());
            preparedStatement.setInt(1, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Order> allOrdersExceptNOTPROCESSEDByUserId(int userId){
        Connection connection = JDBCConnection.getConnection();
        Collection<Order> orders = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getALL_ORDERS_EXCEPT_NOTPROCESSED_BY_ID());
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setStatus(statusFromDatabase(resultSet));
                order.setUserId(userId);
                order.setCost(resultSet.getInt("cost"));
                orders.add(order);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public void changeStatusForPACKED(int orderId) {
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getUPDATE_COST_BY_ORDER_ID());
            preparedStatement.setString(1,"PACKED");
            preparedStatement.setInt(2, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order searchById(int orderId) {
        Connection connection = JDBCConnection.getConnection();
        Order order = new Order();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getSEARCH_BY_ID());
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order.setId(resultSet.getInt("id"));
            order.setUserId(resultSet.getInt("userid"));
            order.setStatus(statusFromDatabase(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    public Collection<Order> ProcessedOrders() {
        Connection connection = JDBCConnection.getConnection();
        Collection<Order> orders = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getPROCESSED_ORDERS());
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
        }
        return orders;
    }

    public void changeStatusForPROCESSED(int orderId) {
        Connection connection = JDBCConnection.getConnection();
        Order order = new Order();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getUPDATE_ORDER_STATUS());
            preparedStatement.setString(1,"PROCESSED");
            preparedStatement.setInt(2, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order searchbyUserIdNotProcessed(int userid, boolean create) {
        Connection connection = JDBCConnection.getConnection();
        Order order = new Order();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getSEARCH_BY_ID_NOTPROCESSED());
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
                order.setCost(resultSet.getInt("cost"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }

    private Order createOrder(int userId) {
        Connection connection = JDBCConnection.getConnection();
        Order order = new Order();

        try {
            PreparedStatement preparedStatementMax = connection.prepareStatement(sql.getMAX_ID());
            ResultSet resultSetMax = preparedStatementMax.executeQuery();
            resultSetMax.next();
            var maxId = resultSetMax.getInt(1);
            order.setId(++maxId);
            order.setUserId(userId);
            order.setStatus(OrderStatus.NOTPROCESSED);
            order.setCost(0);
            PreparedStatement preparedStatementAdd = connection.prepareStatement(sql.getADD_ORDER());
            preparedStatementAdd.setInt(1, order.getId());
            preparedStatementAdd.setInt(2, order.getUserId());
            preparedStatementAdd.setString(3, "NOTPROCESSED");
            preparedStatementAdd.setInt(4,0);
            preparedStatementAdd.execute();
        } catch (SQLException e) {
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
