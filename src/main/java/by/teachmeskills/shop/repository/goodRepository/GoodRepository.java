package by.teachmeskills.shop.repository.goodRepository;

import by.teachmeskills.shop.entity.Good;

import java.util.Collection;
import java.util.List;

public interface GoodRepository {
    public void add(Good good);
    public void deleteById(int id);
    public Collection<Good> all();
    public int priceGood(int goodId);
    public void returnGoodFromBasket(int goodId, int quantity);
    public void buyGood(int goodId, int quantity);
    public Collection<Good> listGetGoodsById(List<Integer> goodIds);
    public Good searchById(int id);


}
