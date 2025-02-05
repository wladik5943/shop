package by.teachmeskills.shop.config.SQL;

import lombok.Getter;

@Getter
public class SQLRequestForOrder {
    private final String SEARCH_BY_ID_NOTPROCESSED = "select * from shop.order where userid = ? and status = 'NOTPROCESSED'";
    private final String MAX_ID = "select max(id) from shop.order";
    private final String ADD_ORDER = "insert into shop.order (id,userid,status,cost)" +
            "values(?,?,?,?)";
    private final String UPDATE_ORDER_STATUS = "update shop.order set status= ? where id = ?";
    private final String PROCESSED_ORDERS = "select * from shop.order where status = 'PROCESSED'";
    private final String SEARCH_BY_ID = "select * from shop.order where id = ?";
    private final String ALL_ORDERS_EXCEPT_NOTPROCESSED_BY_ID = "select * from shop.order where userid = ? and status != 'NOTPROCESSED'";
    private final String DELETE_ORDER = "delete from shop.order where id = ?";
    private final String COST_BY_ORDER_ID = "select cost from shop.order where id = ?";
    private final String UPDATE_COST_BY_ORDER_ID = "update shop.order set cost= ? where id = ?";
}
