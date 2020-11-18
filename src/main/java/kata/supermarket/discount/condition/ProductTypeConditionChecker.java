package kata.supermarket.discount.condition;

import kata.supermarket.product.Item;
import kata.supermarket.product.ProductType;

import java.util.List;

public class ProductTypeConditionChecker implements ConditionChecker {

    private ProductType productType;

    public ProductTypeConditionChecker(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public List<Item> filterItems(List<Item> items) {
        return null;
    }
}