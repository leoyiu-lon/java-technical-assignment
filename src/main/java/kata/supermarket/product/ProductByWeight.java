package kata.supermarket.product;

import java.math.BigDecimal;

public class ProductByWeight extends AbstractProduct{

    private final BigDecimal pricePerKilo;

    public ProductByWeight(ProductName productName, ProductType productType, final BigDecimal pricePerKilo) {
        super(productName, productType);
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
