# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.


- Used chain of responsibility pattern, Null Object pattern
- The design of "ConditionChecker" and "FreeItemDiscount" provide good flexibility, one "FreeItemDiscount" can serve for
   - Buy one get one free per product
   - Buy one get one free per product group
   - Three items for the price of two
   - Plus many other situations
    
- I put "Item" and "Product" into the same package, and the constructor is protected. So only can create Item by Product.
- Unit test is missing. For example, "BasketTest" is not a unit test. It is an integration test. We need to add more unit test with mocking all other class. For example:
  - FreeItemDiscountUnitTest
  -  NoDiscountUnitTest
  -  ProductNameConditionCheckerUnitTest
  -  ProductTypeConditionCheckerUnitTest
 
- The package structure can be improved, but I don't have enough time.
