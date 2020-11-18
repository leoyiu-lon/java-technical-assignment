package kata.supermarket;

import kata.supermarket.product.Item;
import kata.supermarket.product.ProductByWeight;
import kata.supermarket.product.ProductName;
import kata.supermarket.product.ProductType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductByWeightTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(ProductName productName, ProductType productType, String pricePerKilo, String weightInKilos, String expectedPrice) {
        final ProductByWeight productByWeight = new ProductByWeight(productName, productType, new BigDecimal(pricePerKilo));
        final Item weighedItem = productByWeight.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.price());
        assertEquals(productName, weighedItem.getProductName());
        assertEquals(productType, weighedItem.getProductType());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of(ProductName.BEEF, ProductType.MEAT, "100.00", "1.00", "100.00"),
                Arguments.of(ProductName.MILK, ProductType.OTHER, "100.00", "0.33333", "33.33"),
                Arguments.of(ProductName.ONION, ProductType.VEGETABLES, "100.00", "0.33335", "33.34"),
                Arguments.of(ProductName.TOMATO, ProductType.VEGETABLES, "100.00", "0", "0.00")
        );
    }

}