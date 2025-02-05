package by.teachmeskills.shop.config.SQL;

import lombok.Getter;

@Getter
public class SQLRequestForBasket {
    private final String MAX_ID = "select max(id) from shop.basket";
    private final String ADD_BASKET = "insert into shop.basket (id,orderid,goodid,count)" +
            "values(?,?,?,?)";
    private final String BASKET_BY_ORDER_ID = "select * from shop.basket where orderid = ?";
    private final String DELETE_BY_ID = "delete from shop.basket where goodid = ? and orderid = ?";
    private final String DELETE_BY_ORDER_ID ="delete from shop.basket where orderid = ?";
}
