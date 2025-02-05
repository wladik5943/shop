package by.teachmeskills.shop.repository.basketRepository.impl;

import by.teachmeskills.shop.config.Connection.JDBCConnection;
import by.teachmeskills.shop.config.SQL.SQLRequestForBasket;
import by.teachmeskills.shop.entity.Basket;
import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.repository.basketRepository.BasketRepository;
import by.teachmeskills.shop.repository.goodRepository.impl.GoodRepositoryJDBC;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BasketRepositoryJDBC implements BasketRepository {

    private final SQLRequestForBasket sql = new SQLRequestForBasket();

    public void deleteBasketByOrderId(int orderId){
        Connection connection = JDBCConnection.getConnection();
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql.getBASKET_BY_ORDER_ID());
            preparedStatement1.setInt(1,orderId);
            ResultSet resultSet = preparedStatement1.executeQuery();
            GoodRepositoryJDBC goodRepository = new GoodRepositoryJDBC();
            while(resultSet.next()){
                goodRepository.returnGoodFromBasket(resultSet.getInt("goodid"),resultSet.getInt("count"));
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getDELETE_BY_ORDER_ID());
            preparedStatement.setInt(1,orderId);
            preparedStatement.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGoodFromBasketById(int orderId,int goodId){
        Connection connection = JDBCConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getDELETE_BY_ID());
            preparedStatement.setInt(1,goodId);
            preparedStatement.setInt(2,orderId);
            preparedStatement.execute();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Basket basket){
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatementMax = connection.prepareStatement(sql.getMAX_ID());
            ResultSet resultSetMax = preparedStatementMax.executeQuery();
            resultSetMax.next();
            var maxId = resultSetMax.getInt(1);

            basket.setId(++maxId);

            PreparedStatement preparedStatement = connection.prepareStatement(sql.getADD_BASKET());
            preparedStatement.setInt(1,basket.getId());
            preparedStatement.setInt(2,basket.getOrderId());
            preparedStatement.setInt(3,basket.getGoodId());
            preparedStatement.setInt(4,basket.getCount());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Good, Integer> basketByOrderId(int orderId){
        Connection connection = JDBCConnection.getConnection();
        Collection<Good> goods = null;
        Map<Good, Integer> basket = new HashMap<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getBASKET_BY_ORDER_ID());
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Integer,Integer> idAndCount = new HashMap<>();
            LinkedList<Integer> listId = new LinkedList<>();
            while (resultSet.next()) {
                idAndCount.put(resultSet.getInt("goodid"), resultSet.getInt("count"));
                listId.add(resultSet.getInt("goodid"));
            }
            GoodRepositoryJDBC goodRepository = new GoodRepositoryJDBC();
            goods = goodRepository.listGetGoodsById(listId);

            goods.forEach(x ->{
                basket.put(x,idAndCount.get(x.getId()));
            });

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}
