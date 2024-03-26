package by.teachmeskills.shop.api.Order;

import by.teachmeskills.shop.api.Good.GoodResponse;
import by.teachmeskills.shop.entity.Order;
import by.teachmeskills.shop.entity.User;

import java.util.Collection;

public class OrderResponse extends Order {
    private User user;
    private int products;
    private Collection<GoodResponse> basket;

    public Collection<GoodResponse> getBasket() {
        return basket;
    }

    public void setBasket(Collection<GoodResponse> basket) {
        this.basket = basket;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
