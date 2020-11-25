package kata.supermarket;

import kata.supermarket.discount.FreeItemDiscount;
import kata.supermarket.discount.Discount;
import kata.supermarket.discount.NoDiscount;
import kata.supermarket.discount.WeightedProductPercentDiscount;
import kata.supermarket.discount.condition.ProductNameConditionChecker;
import kata.supermarket.discount.condition.ProductTypeConditionChecker;
import kata.supermarket.product.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                buyOneMilkGetOneFree(),
                buyOneVegetableGetOneFree(),
                buyOneGetOneFreeForSingleProductAndProductGroup(),
                buyThreeItemForThePriceOfTwo(),
                weightedProductNotMatchPercentageDiscount(),
                weightedProductWithPercentageDiscount(),
                weightedProductWithPercentageDiscountForOnlyFirstKilo(),
                weightedProductWithPercentageDiscountForTwoKilo(),
                combinedWeightedProductWithPercentageDiscount()
        );
    }


    /**£2 per 1kg of carrots

     Weight: 500g -- Original price: £1 -- Expected discounted price: £1
     Weight: 1kg -- Original price: £2 -- Expected discounted price: £1
     Weight: 1.5kg -- Original price: £3 -- Expected discounted price: £2
     Weight: 2kg -- Original price: £4 -- Expected discounted price: £2
     Weight: 1x 500g, 1x 1.7kg, 1x 2kg -- Original price: £8.40 -- Expected discounted price: £4.40
     **/
    private static Arguments weightedProductNotMatchPercentageDiscount() {
        return Arguments.of("weightedProductNotMatchPercentageDiscount", "1.00", Arrays.asList(halfOfCarrotsItem()),
                new WeightedProductPercentDiscount(new ProductNameConditionChecker(ProductName.CARROTS), new BigDecimal(1), 50));
    }

    private static Arguments weightedProductWithPercentageDiscount() {
        return Arguments.of("weightedProductWithPercentageDiscount", "1.00", Arrays.asList(aKiloOfCarrotsItem()),
                new WeightedProductPercentDiscount(new ProductNameConditionChecker(ProductName.CARROTS), new BigDecimal(1), 50));
    }

    private static Arguments weightedProductWithPercentageDiscountForOnlyFirstKilo() {
        return Arguments.of("weightedProductWithPercentageDiscountForOnlyFirstKilo", "2.00", Arrays.asList(aKiloAndHalfOfCarrotsItem()),
                new WeightedProductPercentDiscount(new ProductNameConditionChecker(ProductName.CARROTS), new BigDecimal(1), 50));
    }

    private static Arguments weightedProductWithPercentageDiscountForTwoKilo() {
        return Arguments.of("weightedProductWithPercentageDiscountForTwoKilo", "2.00", Arrays.asList(twoKiloOfCarrotsItem()),
                new WeightedProductPercentDiscount(new ProductNameConditionChecker(ProductName.CARROTS), new BigDecimal(1), 50));
    }

    private static Arguments buyThreeItemForThePriceOfTwo() {
        return Arguments.of("buyThreeItemForThePriceOfTwo", "0.98", Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk()),
                new FreeItemDiscount(new ProductNameConditionChecker(ProductName.MILK), 3, 1));
    }

    //Weight: 1x 500g, 1x 1.7kg, 1x 2kg -- Original price: £8.40 -- Expected discounted price: £4.40
    private static Arguments combinedWeightedProductWithPercentageDiscount() {
        return Arguments.of("weightedProductNotMatchPercentageDiscount", "4.40", Arrays.asList(halfOfCarrotsItem(), OnePointSevenKiloOfCarrotsItem(), twoKiloOfCarrotsItem()),
                new WeightedProductPercentDiscount(new ProductNameConditionChecker(ProductName.CARROTS), new BigDecimal(1), 50));
    }

    private static Arguments buyOneGetOneFreeForSingleProductAndProductGroup() {
        Discount discount = new FreeItemDiscount(new ProductTypeConditionChecker(ProductType.VEGETABLES), 2, 1);
        discount.setNext(new FreeItemDiscount(new ProductNameConditionChecker(ProductName.MILK), 2, 1));
        return Arguments.of("buyOneGetOneFreeForSingleProductAndProductGroup", "0.79", Arrays.asList(aPackOfOnion(), aPackOfTomato(), aPintOfMilk(), aPintOfMilk()), discount);
    }

    private static Arguments buyOneVegetableGetOneFree() {
        return Arguments.of("buyOneVegetableSGetOneFree", "0.30", Arrays.asList(aPackOfOnion(), aPackOfTomato()),
                new FreeItemDiscount(new ProductTypeConditionChecker(ProductType.VEGETABLES), 2, 1));
    }

    private static Arguments buyOneMilkGetOneFree() {
        return Arguments.of("buyOneMilkGetOneFree", "0.49", Arrays.asList(aPintOfMilk(), aPintOfMilk()),
                new FreeItemDiscount(new ProductNameConditionChecker(ProductName.MILK), 2, 1));
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()), new NoDiscount());
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix()),
                new NoDiscount()
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()), new NoDiscount());
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()), new NoDiscount());
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList(), new NoDiscount());
    }

    private static Item aPintOfMilk() {
        return new ProductByUnit(ProductName.MILK, ProductType.OTHER, new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new ProductByUnit(ProductName.DIGESTIVES, ProductType.OTHER, new BigDecimal("1.55")).oneOf();
    }

    private static ProductByWeight aKiloOfAmericanSweets() {
        return new ProductByWeight(ProductName.SWEETS, ProductType.OTHER, new BigDecimal("4.99"));
    }

    private static ProductByWeight aKiloOfCarrots(){
        return new ProductByWeight(ProductName.CARROTS, ProductType.VEGETABLES, new BigDecimal(2));
    }

    private static Item aKiloOfCarrotsItem(){
        return aKiloOfCarrots().weighing(new BigDecimal(1.0));
    }

    private static Item aKiloAndHalfOfCarrotsItem(){
        return aKiloOfCarrots().weighing(new BigDecimal(1.5));
    }

    private static Item OnePointSevenKiloOfCarrotsItem(){
        return aKiloOfCarrots().weighing(new BigDecimal(1.7));
    }

    private static Item twoKiloOfCarrotsItem(){
        return aKiloOfCarrots().weighing(new BigDecimal(2));
    }

    private static Item halfOfCarrotsItem(){
        return aKiloOfCarrots().weighing(new BigDecimal(.5));
    }


    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static ProductByWeight aKiloOfPickAndMix() {
        return new ProductByWeight(ProductName.PICK_AND_MIX, ProductType.OTHER, new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static Item aPackOfTomato(){
        return new ProductByUnit(ProductName.TOMATO, ProductType.VEGETABLES, new BigDecimal("0.3")).oneOf();
    }

    private static Item aPackOfOnion(){
        return new ProductByUnit(ProductName.ONION, ProductType.VEGETABLES, new BigDecimal("0.2")).oneOf();
    }

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items, Discount availAbleDiscount) {
        final Basket basket = new Basket(availAbleDiscount);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }
}