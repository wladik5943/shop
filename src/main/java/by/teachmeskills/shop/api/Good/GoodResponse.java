package by.teachmeskills.shop.api.Good;

import by.teachmeskills.shop.enums.GoodSubtupe;

public class GoodResponse {
    private int id;
    private int code;
    private String name;
    private GoodSubtupe.subtype subtype;
    private double price;
    private int quantity;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public GoodSubtupe.subtype getSubtype() {
        return subtype;
    }

    public void setSubtype(GoodSubtupe.subtype subtype) {
        this.subtype = subtype;
    }
}
