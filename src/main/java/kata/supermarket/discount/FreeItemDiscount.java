package kata.supermarket.discount;

import kata.supermarket.discount.condition.ConditionChecker;
import kata.supermarket.product.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Item> discountableItems = conditionChecker.filterItems(items);
        if(discountableItems.size() >= numberOfRequiredItem){
            int totalNumberOfDiscount = discountableItems.size()/numberOfRequiredItem;
            discountableItems = discountableItems.stream().sorted(Comparator.comparing(Item::price)).collect(Collectors.toList());
            discountableItems = discountableItems.subList(0, numberOfFreeItem * totalNumberOfDiscount);
            return discountableItems.stream().map(Item::price).reduce(BigDecimal::add).orElse(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
