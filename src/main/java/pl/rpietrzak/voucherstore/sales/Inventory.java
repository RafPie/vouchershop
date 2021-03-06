package pl.rpietrzak.voucherstore.sales;

public interface Inventory {
    boolean isAvailable(String productId);
}
