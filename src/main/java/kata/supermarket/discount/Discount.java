package kata.supermarket.discount;


import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.util.List;

public abstract class Discount {

    public Discount nextDiscount;

    public void setNext(Discount discount) {
        this.nextDiscount = discount;
    }

    public abstract BigDecimal calculateDiscount(List<Item> items);
}
