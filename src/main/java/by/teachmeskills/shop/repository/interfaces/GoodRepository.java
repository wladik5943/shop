package by.teachmeskills.shop.repository.interfaces;

import by.teachmeskills.shop.entity.Good;

import java.util.Collection;

public interface GoodRepository {
    public void add(Good good);
    public void deleteById(int id);
    public Collection<Good> all();
}
