package by.teachmeskills.shop.mapper;


import by.teachmeskills.shop.api.Order.OrderRequest;
import by.teachmeskills.shop.api.Order.OrderResponse;
import by.teachmeskills.shop.entity.Order;

public class OrderMapper {
    public Order toEntity(OrderRequest orderRequest){
        Order order = new Order();
        order.setStatus(orderRequest.getStatus());
        order.setUserId(orderRequest.getUserId());
        order.setId(orderRequest.getId());
        return order;
    }

    public OrderResponse toOrderResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setStatus(order.getStatus());
        return orderResponse;
    }
}
