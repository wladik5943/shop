package by.teachmeskills.shop.config.SQL;

import lombok.Getter;

@Getter
public class SQLRequestForUser {
    private final String ADD_USER = "insert into shop.users (id, name,surname,login,password,role) " +
            "values(?,?,?,?,?,?)";

    private final String MAX_ID = "select max(id) from shop.users";
    private final String LOGINSEARCH = "select * from shop.users where login = ?";
    private final String IDSEARCH = "select * from shop.users where id = ?";
    private final String UPDATE = "update shop.users set name= ? , surname = ? , login = ? , password = ? , role = ? where id = ?";
    private final String ALL = "select * from shop.users";
}
