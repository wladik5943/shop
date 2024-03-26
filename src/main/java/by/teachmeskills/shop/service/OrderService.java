package by.teachmeskills.shop.service;

import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.api.Order.OrderResponse;
import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.entity.User;
import by.teachmeskills.shop.enums.OrderStatus;
import by.teachmeskills.shop.exeption.UniversalExseption;
import by.teachmeskills.shop.mapper.OrderMapper;
import by.teachmeskills.shop.repository.BasketRepository;
import by.teachmeskills.shop.repository.GoodRepository;
import by.teachmeskills.shop.repository.OrderRepository;
import by.teachmeskills.shop.repository.UserRepository;

import java.util.Collection;
import java.util.LinkedList;

public class OrderService {

    public void canselOrder(int orderId){
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.deleteOrder(orderId);
        BasketRepository basketRepository = new BasketRepository();
        basketRepository.deleteBasketByOrderId(orderId);
    }

    public Collection<OrderResponse> userOrders(int userId){
        Collection<OrderResponse> orderResponses = new LinkedList<>();
        OrderRepository orderRepository = new OrderRepository();
        Collection<Order> orders = orderRepository.allOrdersExceptNOTPROCESSEDByUserId(userId);
        UserRepository userRepository = new UserRepository();
        BasketService basketService = new BasketService();
        orders.forEach(x ->{
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(x.getId());
            orderResponse.setStatus(x.getStatus());
            orderResponse.setBasket(basketService.basketByOrderId(x.getId()));
            orderResponse.setUser(userRepository.idSearch(x.getUserId()));
            orderResponse.setProducts(orderResponse.getBasket().size());
            orderResponses.add(orderResponse);
        });
        return orderResponses;

    }

    public void packedOrder(int orderId){
        OrderRepository orderRepository = new OrderRepository();
        orderRepository.changeStatusForPACKED(orderId);
    }

    public OrderResponse OrderById(int orderId){
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.searchById(orderId);
        UserRepository userRepository = new UserRepository();
        User user = userRepository.idSearch(order.getUserId());
        OrderResponse orderResponse = new OrderResponse();
        OrderMapper orderMapper = new OrderMapper();
        orderResponse = orderMapper.toOrderResponse(order);
        orderResponse.setUser(user);
        return orderResponse;
    }

    public Collection<OrderResponse> processedOrders(){
        OrderRepository orderRepository = new OrderRepository();
        UserRepository userRepository = new UserRepository();
        BasketRepository basketRepository = new BasketRepository();
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
        GoodRepository goodRepository = new GoodRepository();
        for(GoodResponse temp: basket)
            if(!temp.isStock())
                throw new UniversalExseption("не достаточно товара");
        OrderRepository orderRepository = new OrderRepository();
        Order order = orderRepository.searchbyUserIdNotProcessed(userId, false);
        if(order == null || basket.isEmpty())
            throw new UniversalExseption("корзина пуста");
        orderRepository.changeStatusForPROCESSED(order.getId());
        basket.forEach(x ->{
            goodRepository.buyGood(x.getId(),x.getQuantity());
        });
    }
}
