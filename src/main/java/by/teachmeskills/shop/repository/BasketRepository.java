package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.Basket;
import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.entity.Order;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BasketRepository {

    private final String URL = "jdbc:postgresql://localhost:5432/databaseforshop";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "1234";

    private final String MAX_ID = "select max(id) from shop.basket";
    private final String ADD_BASKET = "insert into shop.basket (id,orderid,goodid,count)" +
            "values(?,?,?,?)";
    private final String BASKET_BY_ORDER_ID = "select * from shop.basket where orderid = ?";
    private final String DELETE_BY_ID = "delete from shop.basket where goodid = ? and orderid = ?";
    private final String DELETE_BY_ORDER_ID ="delete from shop.basket where orderid = ?";
    public void deleteBasketByOrderId(int orderId){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement1 = connection.prepareStatement(BASKET_BY_ORDER_ID);
            preparedStatement1.setInt(1,orderId);
            ResultSet resultSet = preparedStatement1.executeQuery();
            GoodRepository goodRepository = new GoodRepository();
            while(resultSet.next()){
                goodRepository.returnGoodFromBasket(resultSet.getInt("goodid"),resultSet.getInt("count"));
            }
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ORDER_ID);
            preparedStatement.setInt(1,orderId);
            preparedStatement.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGoodFromBasketById(int orderId,int goodId){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,goodId);
            preparedStatement.setInt(2,orderId);
            preparedStatement.execute();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Basket basket){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
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

    public Map<Good, Integer> basketByOrderId(int orderId){
        Connection connection = null;
        Collection<Good> goods = null;
        Map<Good, Integer> basket = new HashMap<>();
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(BASKET_BY_ORDER_ID);
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Integer,Integer> idAndCount = new HashMap<>();
            LinkedList<Integer> listId = new LinkedList<>();
            while (resultSet.next()) {
                idAndCount.put(resultSet.getInt("goodid"), resultSet.getInt("count"));
                listId.add(resultSet.getInt("goodid"));
            }
            GoodRepository goodRepository = new GoodRepository();
            goods = goodRepository.listGetGoodsById(listId);

            goods.forEach(x ->{
                basket.put(x,idAndCount.get(x.getId()));
            });

        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}
