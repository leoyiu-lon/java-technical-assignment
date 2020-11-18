package kata.supermarket.product;

import java.math.BigDecimal;

public class ProductByUnit {

    private final BigDecimal pricePerUnit;

    public ProductByUnit(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
