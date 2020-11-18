package kata.supermarket;

import kata.supermarket.discount.condition.ProductTypeConditionChecker;
import kata.supermarket.product.Item;
import kata.supermarket.product.ProductType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTypeConditionCheckerUnitTest {

    @Mock Item itemA;
    @Mock Item itemB;
    @Mock Item itemC;

    @Test
    public void shouldAbleToFilterItemByProductType() {
        when(itemA.getProductType()).thenReturn(ProductType.VEGETABLES);
        when(itemB.getProductType()).thenReturn(ProductType.MEAT);
        when(itemC.getProductType()).thenReturn(ProductType.VEGETABLES);

        List<Item> result = new ProductTypeConditionChecker(ProductType.VEGETABLES).filterItems(Arrays.asList(itemA, itemB, itemC));
        assertEquals(2, result.size());
        assertEquals(ProductType.VEGETABLES, result.get(0).getProductType());
        assertEquals(ProductType.VEGETABLES, result.get(1).getProductType());
    }

}
