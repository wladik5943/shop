package by.teachmeskills.shop.config.SQL;

import lombok.Getter;

@Getter
public class SQLRequestForGood {
    private final String ADD_GOOD = "insert into  shop.goods(id, code, name, subtype, quantity, price)" +
            " values (?,?,?,?,?,?);";

    private final String MAX_ID = "select max(id) from shop.goods";
    private final String SEARCH_BY_ID = "select * from shop.goods where id = ?";
    private final String QUANTITY_BY_ID = "select quantity from shop.goods where id = ?";

    private final String UPDATE_QUANTITY_BY_ID = "update shop.goods set quantity = ? where id = ?";
    private final String PRICE_BY_ORDER_ID = "select price from shop.goods where id = ?";
    private final String ALL_GOODS = "select * from shop.goods";
}
