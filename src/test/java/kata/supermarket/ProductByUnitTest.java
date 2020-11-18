package kata.supermarket;

import kata.supermarket.product.Item;
import kata.supermarket.product.ProductByUnit;
import kata.supermarket.product.ProductName;
import kata.supermarket.product.ProductType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductByUnitTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        Item item = new ProductByUnit(ProductName.BEEF, ProductType.MEAT, price).oneOf();
        assertEquals(price, item.price());
        assertEquals(ProductName.BEEF, item.getProductName());
        assertEquals(ProductType.MEAT, item.getProductType());
    }
}