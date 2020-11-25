package kata.supermarket.discount;

import kata.supermarket.discount.condition.ConditionChecker;
import kata.supermarket.product.Item;
import kata.supermarket.product.ProductName;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightedProductPercentDiscount extends Discount {

    private ConditionChecker conditionChecker;
    private BigDecimal requestedWeight;
    private int discount;

    public WeightedProductPercentDiscount(ConditionChecker conditionChecker, BigDecimal requestedWeight, int discount) {
        this.conditionChecker = conditionChecker;
        this.requestedWeight = requestedWeight;
        this.discount = discount;
    }

    public BigDecimal calculateDiscount(List<Item> items) {
        List<Item> discountAbleItems = conditionChecker.filterItems(items);
        BigDecimal totalPriceForDiscount = BigDecimal.ZERO;

        Map<ProductName, BigDecimal> pricePerProductMap = new HashMap<>();
        Map<ProductName, BigDecimal> weightPerProductMap = new HashMap<>();

        for (Item item : discountAbleItems) {
            if (!pricePerProductMap.containsKey(item.getProductName())) {
                pricePerProductMap.put(item.getProductName(), item.price().divide(item.getWeight()));
            }
            if (!weightPerProductMap.containsKey(item.getProductName())) {
                weightPerProductMap.put(item.getProductName(), BigDecimal.ZERO);
            }
            weightPerProductMap.put(item.getProductName(),weightPerProductMap.get(item.getProductName()).add(item.getWeight()));
        }

        for (Map.Entry<ProductName, BigDecimal> weightPerProduct : weightPerProductMap.entrySet()) {
            BigDecimal numberOfDiscount = requestedWeight.multiply(new BigDecimal((weightPerProduct.getValue().divide(requestedWeight)).intValue()));
            totalPriceForDiscount = totalPriceForDiscount.add(pricePerProductMap.get(weightPerProduct.getKey()).multiply(numberOfDiscount));
        }

        return totalPriceForDiscount.multiply(new BigDecimal((double) discount / 100)).setScale(2, RoundingMode.HALF_UP);
    }
}
