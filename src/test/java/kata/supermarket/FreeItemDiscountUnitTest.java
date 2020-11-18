package kata.supermarket;

import kata.supermarket.discount.FreeItemDiscount;
import kata.supermarket.discount.condition.ConditionChecker;
import kata.supermarket.product.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FreeItemDiscountUnitTest {

    @Mock Item itemA;
    @Mock Item itemB;
    @Mock Item itemC;
    @Mock ConditionChecker conditionChecker;

    @Test
    public void shouldAbleToReturnThePriceOfTheCheapestFreeItem() {
        when(itemA.price()).thenReturn(new BigDecimal(10));
        when(itemB.price()).thenReturn(new BigDecimal(12));
        when(itemC.price()).thenReturn(new BigDecimal(13));
        List<Item> testingItems = Arrays.asList(itemA, itemB, itemC);
        when(conditionChecker.filterItems(any())).thenReturn(testingItems);

        FreeItemDiscount freeItemDiscount = new FreeItemDiscount(conditionChecker, 3, 2);
        assertEquals(new BigDecimal(22).setScale(2, RoundingMode.HALF_UP), freeItemDiscount.calculateDiscount(testingItems));
    }
}
