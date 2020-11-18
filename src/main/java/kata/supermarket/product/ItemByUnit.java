package kata.supermarket.product;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final ProductByUnit productByUnit;

    ItemByUnit(final ProductByUnit productByUnit) {
        this.productByUnit = productByUnit;
    }

    public BigDecimal price() {
        return productByUnit.pricePerUnit();
    }

    @Override
    public ProductName getProductName() {
        return productByUnit.getProductName();
    }

    @Override
    public ProductType getProductType() {
        return productByUnit.getProductType();
    }

}
