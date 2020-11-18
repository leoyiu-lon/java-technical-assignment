package kata.supermarket.discount.condition;

import kata.supermarket.product.Item;
import kata.supermarket.product.ProductName;

import java.util.List;
import java.util.stream.Collectors;

public class ProductNameConditionChecker implements ConditionChecker{
    public final ProductName productName;

    public ProductNameConditionChecker(final ProductName productName) {
        this.productName = productName;
    }

    @Override
    public List<Item> filterItems(List<Item> items){
        return items.stream().filter(item -> productName == item.getProductName()).collect(Collectors.toList());
    }
}
