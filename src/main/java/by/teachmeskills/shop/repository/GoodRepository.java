package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.entity.ShopEntity;
import by.teachmeskills.shop.enums.GoodSubtupe;
import by.teachmeskills.shop.repository.interfaces.UserRepository;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;


public class GoodRepository implements by.teachmeskills.shop.repository.interfaces.GoodRepository {

    private final String ADD_GOOD = "insert into  shop.goods(id, code, name, subtype, quantity, price)" +
            " values (?,?,?,?,?,?);";

    private final String MAX_ID = "select max(id) from shop.goods";

    @Override
    public void add(Good good) {
//        Good good = (Good)ent;
//        Collection<Good> goods = SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
//        good.setId(goods.size() + 1);
//        goods.add(good);
//        SerializeGood.serialize(goods,"D:\\progects\\shop\\src\\main\\resources\\goods");


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
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_GOOD);
            preparedStatement.setInt(1, ++maxId);
            preparedStatement.setInt(2, good.getCode());
            preparedStatement.setString(3, good.getName());
            preparedStatement.setString(4, subtypeInDatabase(good));
            preparedStatement.setInt(5, good.getQuantity());
            preparedStatement.setDouble(6, good.getPrice());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        Collection<Good> goods = SerializeGood.deSerialize("D:\\progects\\shop\\src\\main\\resources\\goods");
        for (Good temp : goods) {
            if (temp.getId() == id) {
                goods.remove(temp);
                break;
            }
        }
        SerializeGood.serialize(goods, "D:\\progects\\shop\\src\\main\\resources\\goods");
    }

    @Override
    public Collection<Good> all() {
        Collection<Good> goods = new LinkedList<>();
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/databaseforshop";
        String username = "postgres";
        String password = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from shop.goods");
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
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
