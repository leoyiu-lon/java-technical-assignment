package kata.supermarket.discount;


import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.util.List;

public class NoDiscount extends Discount{
    @Override
    public BigDecimal calculateDiscount(List<Item> items) {
        return BigDecimal.ZERO;
    }
}
