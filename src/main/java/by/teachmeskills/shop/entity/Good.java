package by.teachmeskills.shop.entity;

import by.teachmeskills.shop.enums.GoodSubtupe;

import java.io.Serializable;

public class Good extends ShopEntity implements Serializable {
    private int id;
    private int code;
    private String name;
    private GoodSubtupe.subtype subtype;

    public GoodSubtupe.subtype getSubtype() {
        return subtype;
    }

    public void setSubtype(GoodSubtupe.subtype subtype) {
        this.subtype = subtype;
    }

    private double price;
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
