package by.teachmeskills.shop.repository.basketRepository;

import by.teachmeskills.shop.entity.Basket;
import by.teachmeskills.shop.entity.Good;

import java.util.Map;

public interface BasketRepository {
    public void deleteBasketByOrderId(int orderId);
    public void deleteGoodFromBasketById(int orderId,int goodId);
    public void add(Basket basket);
    public Map<Good, Integer> basketByOrderId(int orderId);
}
