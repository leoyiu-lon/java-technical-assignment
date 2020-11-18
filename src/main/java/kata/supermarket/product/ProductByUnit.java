package kata.supermarket.product;

import java.math.BigDecimal;

public class ProductByUnit extends AbstractProduct{

    private final BigDecimal pricePerUnit;

    public ProductByUnit(ProductName productName, ProductType productType, BigDecimal price) {
        super(productName, productType);
        this.pricePerUnit = price;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
