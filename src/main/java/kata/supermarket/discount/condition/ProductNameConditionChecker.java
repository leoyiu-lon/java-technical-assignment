package kata.supermarket.discount.condition;

import kata.supermarket.product.Item;
import kata.supermarket.product.ProductName;

import java.util.List;

public class ProductNameConditionChecker implements ConditionChecker{
    public final ProductName productName;

    public ProductNameConditionChecker(final ProductName productName) {
        this.productName = productName;
    }

    @Override
    public List<Item> filterItems(List<Item> items){
        return null;
    }
}
