package kata.supermarket.discount;

import kata.supermarket.discount.condition.ConditionChecker;
import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.util.List;

public class FreeItemDiscount extends Discount{

    private ConditionChecker conditionChecker;
    private int numberOfRequiredItem;
    private int numberOfFreeItem;

    public FreeItemDiscount(ConditionChecker conditionChecker, int numberOfRequiredItem, int numberOfFreeItem) {
        this.conditionChecker = conditionChecker;
        this.numberOfRequiredItem = numberOfRequiredItem;
        this.numberOfFreeItem = numberOfFreeItem;
    }

    @Override
    public BigDecimal calculateDiscount(List<Item> items) {
        return BigDecimal.ZERO;
    }
}