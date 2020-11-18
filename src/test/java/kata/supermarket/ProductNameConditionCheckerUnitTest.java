package kata.supermarket;

import kata.supermarket.discount.condition.ProductNameConditionChecker;
import kata.supermarket.product.Item;
import kata.supermarket.product.ProductName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductNameConditionCheckerUnitTest {

    @Mock Item itemA;
    @Mock Item itemB;
    @Mock Item itemC;

    @Test
    public void shouldAbleToFilterItemByProductName() {
        when(itemA.getProductName()).thenReturn(ProductName.BEEF);
        when(itemB.getProductName()).thenReturn(ProductName.BEEF);
        when(itemC.getProductName()).thenReturn(ProductName.MILK);

        List<Item> result = new ProductNameConditionChecker(ProductName.BEEF).filterItems(Arrays.asList(itemA, itemB, itemC));
        assertEquals(2, result.size());
        assertEquals(ProductName.BEEF, result.get(0).getProductName());
        assertEquals(ProductName.BEEF, result.get(1).getProductName());
    }
}
