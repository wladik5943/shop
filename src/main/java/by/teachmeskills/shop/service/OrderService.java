package by.teachmeskills.shop.service;

import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.api.Order.OrderResponse;
import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.OrderStatus;
import by.teachmeskills.shop.exeption.UniversalExseption;
import by.teachmeskills.shop.mapper.OrderMapper;
import by.teachmeskills.shop.repository.basketRepository.impl.BasketRepositoryJDBC;
import by.teachmeskills.shop.repository.goodRepository.impl.GoodRepositoryJDBC;
import by.teachmeskills.shop.repository.orderRepository.impl.OrderRepositoryJDBC;
import by.teachmeskills.shop.repository.userRepository.impl.UserRepositoryJDBC;

import java.util.Collection;
import java.util.LinkedList;

public class OrderService {

    public void canselOrder(int orderId){
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        orderRepository.deleteOrder(orderId);
        BasketRepositoryJDBC basketRepository = new BasketRepositoryJDBC();
        basketRepository.deleteBasketByOrderId(orderId);
    }

    public Collection<OrderResponse> userOrders(int userId){
        Collection<OrderResponse> orderResponses = new LinkedList<>();
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        Collection<Order> orders = orderRepository.allOrdersExceptNOTPROCESSEDByUserId(userId);
        UserRepositoryJDBC userRepository = new UserRepositoryJDBC();
        BasketService basketService = new BasketService();
        orders.forEach(x ->{
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(x.getId());
            orderResponse.setStatus(x.getStatus());
            orderResponse.setBasket(basketService.basketByOrderId(x.getId()));
            orderResponse.setUser(userRepository.idSearch(x.getUserId()));
            orderResponse.setProducts(orderResponse.getBasket().size());
            orderResponse.setCost(x.getCost());
            orderResponses.add(orderResponse);
        });
        return orderResponses;

    }

    public void packedOrder(int orderId){
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        orderRepository.changeStatusForPACKED(orderId);
    }

    public OrderResponse OrderById(int orderId){
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        Order order = orderRepository.searchById(orderId);
        UserRepositoryJDBC userRepository = new UserRepositoryJDBC();
        User user = userRepository.idSearch(order.getUserId());
        OrderResponse orderResponse = new OrderResponse();
        OrderMapper orderMapper = new OrderMapper();
        orderResponse = orderMapper.toOrderResponse(order);
        orderResponse.setUser(user);
        return orderResponse;
    }

    public Collection<OrderResponse> processedOrders(){
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        UserRepositoryJDBC userRepository = new UserRepositoryJDBC();
        BasketRepositoryJDBC basketRepository = new BasketRepositoryJDBC();
        Collection<Order> orders = orderRepository.ProcessedOrders();
        Collection<OrderResponse> processedOrders = new LinkedList<>();
        orders.forEach(x ->{
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(x.getId());
            orderResponse.setStatus(OrderStatus.PROCESSED);
            orderResponse.setUser(userRepository.idSearch(x.getUserId()));
            orderResponse.setProducts(basketRepository.basketByOrderId(x.getId()).size());
            processedOrders.add(orderResponse);
        });
        return processedOrders;
    }

    public void placeOrder(int userId, Collection<GoodResponse> basket) throws UniversalExseption{
        GoodRepositoryJDBC goodRepository = new GoodRepositoryJDBC();
        for(GoodResponse temp: basket)
            if(!temp.isStock())
                throw new UniversalExseption("не достаточно товара");
        OrderRepositoryJDBC orderRepository = new OrderRepositoryJDBC();
        Order order = orderRepository.searchbyUserIdNotProcessed(userId, false);
        if(order == null || basket.isEmpty())
            throw new UniversalExseption("корзина пуста");
        orderRepository.changeStatusForPROCESSED(order.getId());
        basket.forEach(x ->{
            goodRepository.buyGood(x.getId(),x.getQuantity());
        });
    }
}
