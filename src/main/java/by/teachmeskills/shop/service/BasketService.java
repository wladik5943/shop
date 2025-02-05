package by.teachmeskills.shop.service;

import by.teachmeskills.shop.api.Basket.BasketRequest;
import by.teachmeskills.shop.api.Basket.BasketResponse;
import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.entity.Basket;
import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.mapper.BasketMapper;
import by.teachmeskills.shop.mapper.GoodMapper;
import by.teachmeskills.shop.repository.basketRepository.impl.BasketRepositoryJDBC;
import by.teachmeskills.shop.repository.goodRepository.impl.GoodRepositoryJDBC;
import by.teachmeskills.shop.repository.orderRepository.impl.OrderRepositoryJDBC;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class BasketService {


    public Collection<GoodResponse> basketByOrderId(int orderId){
        BasketRepositoryJDBC basketRepository = new BasketRepositoryJDBC();
        GoodMapper goodMapper = new GoodMapper();
        Map<Good, Integer> goodIntegerMap = basketRepository.basketByOrderId(orderId);
        Collection<GoodResponse> basket = new LinkedList<>();
        goodIntegerMap.forEach((x,y) ->{
            GoodResponse good = new GoodResponse();
            good = goodMapper.toGoodResponse(x);
            good.setQuantity(y);
            basket.add(good);
        });
        return basket;
    }

    public void deleteGoodFromBasket(int userId,int goodId){
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        Order order = orderRepository.searchbyUserIdNotProcessed(userId, false);
        BasketRepositoryJDBC basketRepository = new BasketRepositoryJDBC();
        basketRepository.deleteGoodFromBasketById(order.getId(),goodId);
    }

    public BasketResponse addBasket(int userId, BasketRequest basketRequest){
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        Order order = orderRepository.searchbyUserIdNotProcessed(userId, true);
        BasketMapper basketMapper = new BasketMapper();
        Basket basket = basketMapper.toEntity(basketRequest);
        basket.setOrderId(order.getId());
        BasketRepositoryJDBC basketRepository = new BasketRepositoryJDBC();
        basketRepository.add(basket);
        int cost = orderRepository.orderCost(order.getId());
        GoodRepositoryJDBC goodRepository = new GoodRepositoryJDBC();
        int price = goodRepository.priceGood(basket.getGoodId());
        cost += price * basket.getCount();
        orderRepository.updateCost(order.getId(),cost);
        return basketMapper.toBasketResponse(basket);
    }

    public Collection<GoodResponse> userBasket(int userId){
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        Order order = orderRepository.searchbyUserIdNotProcessed(userId, false);
        if(order == null){
            return null;
        }
        BasketRepositoryJDBC basketRepository = new BasketRepositoryJDBC();
        Map<Good, Integer> basket = basketRepository.basketByOrderId(order.getId());
        Collection<GoodResponse> goodResponses = new LinkedList<>();
        GoodMapper goodMapper = new GoodMapper();
        basket.forEach( (x,y)->{
            GoodResponse good;
            if(x.getQuantity() < y){
                good = goodMapper.toGoodResponse(x);
                good.setStock(false);
                good.setQuantity(y);
                goodResponses.add(good);
            }
            else {
                good = goodMapper.toGoodResponse(x);
                good.setStock(true);
                good.setQuantity(y);
                goodResponses.add(good);
            }
        });
        return goodResponses;
    }
}
