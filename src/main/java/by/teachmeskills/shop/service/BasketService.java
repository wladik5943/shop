package by.teachmeskills.shop.service;

import by.teachmeskills.shop.api.Basket.BasketRequest;
import by.teachmeskills.shop.api.Basket.BasketResponse;
import by.teachmeskills.shop.entity.Basket;
import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.mapper.BasketMapper;
import by.teachmeskills.shop.repository.BasketRepository;
import by.teachmeskills.shop.repository.OrderRepository;

public class BasketService {
    public BasketResponse addBasket(int userId, BasketRequest basketRequest){
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.searchbyUserIdNotProcessed(userId);
        BasketMapper basketMapper = new BasketMapper();
        Basket basket = basketMapper.toEntity(basketRequest);
        basket.setOrderId(order.getId());
        BasketRepository basketRepository = new BasketRepository();
        basketRepository.add(basket);
        return basketMapper.toBasketResponse(basket);
    }
}
