package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.ShopEntity;
import by.teachmeskills.shop.entity.User;

import java.util.Collection;

public  interface ShopRepository {
    public void add(ShopEntity ent);
    public void deleteByld(int userId);

}
