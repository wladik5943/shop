package by.teachmeskills.shop.repository;

import by.teachmeskills.shop.entity.User;

import java.util.Collection;

public  interface ShopRepository {
    public void add(User user);
    public void deleteByld(int userId);
    public Collection<User> allUsers();

}
