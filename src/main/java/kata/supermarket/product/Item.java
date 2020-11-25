package kata.supermarket.product;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();
    public ProductName getProductName();
    public ProductType getProductType();
    public BigDecimal getWeight();

}
