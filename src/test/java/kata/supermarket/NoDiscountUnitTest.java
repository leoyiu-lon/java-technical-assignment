package kata.supermarket;

import kata.supermarket.discount.NoDiscount;
import kata.supermarket.product.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class NoDiscountUnitTest {

    @Mock
    Item itemA;

    @Test
    public void shouldReturnZero(){
        List<Item> testingItems = Arrays.asList(itemA);
        NoDiscount noDiscount = new NoDiscount();
        assertEquals(BigDecimal.ZERO, noDiscount.calculateDiscount(testingItems));
    }
}
