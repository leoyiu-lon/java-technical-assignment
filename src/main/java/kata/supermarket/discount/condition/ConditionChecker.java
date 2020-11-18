package kata.supermarket.discount.condition;


import kata.supermarket.product.Item;

import java.util.List;

public interface ConditionChecker {
    public List<Item> filterItems(List<Item> items);
}