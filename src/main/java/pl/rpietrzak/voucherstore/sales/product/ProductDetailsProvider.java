package pl.rpietrzak.voucherstore.sales.product;

public interface ProductDetailsProvider {
    ProductDetails getByProductId(String productId);
}
