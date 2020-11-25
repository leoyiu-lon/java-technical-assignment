package kata.supermarket;

import kata.supermarket.discount.FreeItemDiscount;
import kata.supermarket.discount.WeightedProductPercentDiscount;
import kata.supermarket.discount.condition.ConditionChecker;
import kata.supermarket.product.Item;
import kata.supermarket.product.ProductName;
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
public class WeightedProductPercentDiscountUnitTest {

    @Mock Item itemA;
    @Mock Item itemB;
    @Mock Item itemC;
    @Mock ConditionChecker conditionChecker;

    @Test
    public void shouldAbleToReturn50PercentageDiscount() {
        when(itemA.price()).thenReturn(new BigDecimal(10));
        when(itemA.getWeight()).thenReturn(new BigDecimal(1));
        List<Item> testingItems = Arrays.asList(itemA);
        when(conditionChecker.filterItems(any())).thenReturn(testingItems);

        WeightedProductPercentDiscount weightedProductPercentDiscount = new WeightedProductPercentDiscount(conditionChecker, new BigDecimal(1), 50);
        assertEquals(new BigDecimal(5).setScale(2, RoundingMode.HALF_UP), weightedProductPercentDiscount.calculateDiscount(testingItems));
    }

    @Test
    public void shouldAbleToReturn50PercentageDiscountForFirstKilo() {
        when(itemA.price()).thenReturn(new BigDecimal(3));
        when(itemA.getWeight()).thenReturn(new BigDecimal(1.5));
        List<Item> testingItems = Arrays.asList(itemA);
        when(conditionChecker.filterItems(any())).thenReturn(testingItems);

        WeightedProductPercentDiscount weightedProductPercentDiscount = new WeightedProductPercentDiscount(conditionChecker, new BigDecimal(1), 50);
        assertEquals(new BigDecimal(1).setScale(2, RoundingMode.HALF_UP), weightedProductPercentDiscount.calculateDiscount(testingItems));
    }

    @Test
    public void shouldReturn50PercentageDiscountForCombinedProduct() {
        when(itemA.getProductName()).thenReturn(ProductName.CARROTS);
        when(itemB.getProductName()).thenReturn(ProductName.CARROTS);
        when(itemC.getProductName()).thenReturn(ProductName.CARROTS);

        when(itemA.price()).thenReturn(new BigDecimal(1));
        when(itemA.getWeight()).thenReturn(new BigDecimal(.5));
        when(itemB.getWeight()).thenReturn(new BigDecimal(1.7));
        when(itemC.getWeight()).thenReturn(new BigDecimal(2));

        List<Item> testingItems = Arrays.asList(itemA, itemB, itemC);
        when(conditionChecker.filterItems(any())).thenReturn(testingItems);

        WeightedProductPercentDiscount weightedProductPercentDiscount = new WeightedProductPercentDiscount(conditionChecker, new BigDecimal(1), 50);
        assertEquals(new BigDecimal(4).setScale(2, RoundingMode.HALF_UP), weightedProductPercentDiscount.calculateDiscount(testingItems));
    }




}
