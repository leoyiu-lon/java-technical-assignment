package kata.supermarket.product;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final ProductByWeight product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final ProductByWeight product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
