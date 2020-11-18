package kata.supermarket.product;

import java.math.BigDecimal;

public class ProductByWeight {

    private final BigDecimal pricePerKilo;

    public ProductByWeight(final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
