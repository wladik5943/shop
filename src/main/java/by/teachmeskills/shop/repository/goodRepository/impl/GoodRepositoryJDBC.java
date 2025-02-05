package by.teachmeskills.shop.repository.goodRepository.impl;

import by.teachmeskills.shop.config.Connection.JDBCConnection;
import by.teachmeskills.shop.config.SQL.SQLRequestForGood;
import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.enums.GoodSubtupe;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class GoodRepositoryJDBC implements by.teachmeskills.shop.repository.goodRepository.GoodRepository {

    private final  SQLRequestForGood sql = new SQLRequestForGood();

    @Override
    public int priceGood(int goodId) {
        Connection connection = JDBCConnection.getConnection();
        int price;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getPRICE_BY_ORDER_ID());
            preparedStatement.setInt(1, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            price = resultSet.getInt("price");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

    @Override
    public void returnGoodFromBasket(int goodId, int quantity) {
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getQUANTITY_BY_ID());
            preparedStatement.setInt(1, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int quantityFromDataBase = resultSet.getInt("quantity");
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql.getUPDATE_QUANTITY_BY_ID());
            preparedStatement1.setInt(1, quantityFromDataBase + quantity);
            preparedStatement1.setInt(2, goodId);
            preparedStatement1.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buyGood(int goodId, int quantity) {
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getQUANTITY_BY_ID());
            preparedStatement.setInt(1, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int quantityInDataBase = resultSet.getInt("quantity");
            quantityInDataBase -= quantity;
            PreparedStatement preparedStatementBuy = connection.prepareStatement(sql.getPRICE_BY_ORDER_ID());
            preparedStatementBuy.setInt(1, quantityInDataBase);
            preparedStatementBuy.setInt(2, goodId);
            preparedStatementBuy.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Good> listGetGoodsById(List<Integer> goodIds) {
        Collection<Good> goods = new LinkedList<>();
        goodIds.forEach(x -> {
            goods.add(searchById(x));
        });
        return goods;
    }

    @Override
    public Good searchById(int id) {
        Connection connection = JDBCConnection.getConnection();
        Good good = new Good();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getSEARCH_BY_ID());
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            good.setId(resultSet.getInt("id"));
            good.setName(resultSet.getString("name"));
            good.setCode(resultSet.getInt("code"));
            good.setPrice(resultSet.getInt("price"));
            good.setQuantity(resultSet.getInt("quantity"));
            good.setSubtype(subtypeFromDatabase(resultSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return good;
    }

    @Override
    public void add(Good good) {
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatementMax = connection.prepareStatement(sql.getMAX_ID());
            ResultSet resultSet = preparedStatementMax.executeQuery();
            resultSet.next();
            var maxId = resultSet.getInt(1);
            PreparedStatement preparedStatement = connection.prepareStatement(sql.getADD_GOOD());
            preparedStatement.setInt(1, ++maxId);
            preparedStatement.setInt(2, good.getCode());
            preparedStatement.setString(3, good.getName());
            preparedStatement.setString(4, subtypeInDatabase(good));
            preparedStatement.setInt(5, good.getQuantity());
            preparedStatement.setDouble(6, good.getPrice());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {

    }
    @Override
    public Collection<Good> all() {
        Collection<Good> goods = new LinkedList<>();
        Connection connection = JDBCConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql.getALL_GOODS());
            while (resultSet.next()) {
                Good good = new Good();
                good.setId(resultSet.getInt("id"));
                good.setName(resultSet.getString("name"));
                good.setCode(resultSet.getInt("code"));
                good.setQuantity(resultSet.getInt("quantity"));
                good.setPrice(resultSet.getInt("price"));
                good.setSubtype(subtypeFromDatabase(resultSet));
                goods.add(good);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return goods;
        }
    }

    private GoodSubtupe subtypeFromDatabase(ResultSet resultSet) {
        try {
            switch (resultSet.getString("subtype")) {
                case "PHONE" -> {
                    return GoodSubtupe.PHONE;
                }
                case "COMPUTER" -> {
                    return GoodSubtupe.COMPUTER;
                }
                case "AUDIO" -> {
                    return GoodSubtupe.AUDIO;
                }
                case "WATCH" -> {
                    return GoodSubtupe.WATCH;
                }
                case "TV" -> {
                    return GoodSubtupe.TV;
                }
                case "APPLIANCES" -> {
                    return GoodSubtupe.APPLIANCES;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private String subtypeInDatabase(Good good) {
        switch (good.getSubtype()) {
            case PHONE -> {
                return "PHONE";
            }
            case COMPUTER -> {
                return "COMPUTER";
            }
            case AUDIO -> {
                return "AUDIO";
            }
            case WATCH -> {
                return "WATCH";
            }
            case TV -> {
                return "TV";
            }
            case APPLIANCES -> {
                return "APPLIANCES";
            }
        }
        return null;
    }
}
