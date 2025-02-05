package by.teachmeskills.shop.api.Good;

import by.teachmeskills.shop.entity.Good;
import by.teachmeskills.shop.enums.GoodSubtupe;

public class GoodResponse extends Good {
    private boolean stock;

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }
}
