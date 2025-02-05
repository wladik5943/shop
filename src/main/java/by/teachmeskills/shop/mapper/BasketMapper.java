package by.teachmeskills.shop.mapper;

import by.teachmeskills.shop.api.Basket.BasketRequest;
import by.teachmeskills.shop.api.Basket.BasketResponse;
import by.teachmeskills.shop.api.Good.GoodRequest;
import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.entity.Basket;
import by.teachmeskills.shop.entity.Good;

public class BasketMapper {
    public Basket toEntity(BasketRequest basketRequest){
        Basket basket = new Basket();
        basket.setGoodId(basketRequest.getGoodId());
        basket.setOrderId(basketRequest.getOrderId());
        basket.setCount(basketRequest.getCount());
        return basket;
    }

    public BasketResponse toBasketResponse(Basket basket){
        BasketResponse basketResponse = new BasketResponse();
        basketResponse.setId(basket.getId());
        basketResponse.setOrderId(basket.getOrderId());
        basketResponse.setGoodId(basket.getGoodId());
        basketResponse.setCount(basket.getCount());
        return basketResponse;
    }
}
