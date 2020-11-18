package kata.supermarket.product;

public class AbstractProduct {
    private final ProductName productName;
    private final ProductType productType;

    public AbstractProduct(ProductName productName, ProductType productType) {
        this.productName = productName;
        this.productType = productType;
    }

    public ProductName getProductName() {
        return productName;
    }

    public ProductType getProductType() {
        return productType;
    }
}
