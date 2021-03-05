package pl.rpietrzak.vouchershop.sales;

import pl.rpietrzak.vouchershop.catalog.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {
    private final Map<String, Product> products;
    private final Map<String, Integer> productsQuantities;

    public Basket() {
        this.products = new HashMap<>();
        this.productsQuantities = new HashMap<>();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void add(Product product) {
        if (isInBasket(product)) {
            increaseQuantity(product);
        } else {
            putIntoBasket(product);
        }
    }

    private void putIntoBasket(Product product) {
        productsQuantities.put(product.getId(), 1);
        products.put(product.getId(), product);
    }

    private void increaseQuantity(Product product) {
         productsQuantities.put(
                product.getId(),
                productsQuantities.get(product.getId()) + 1);
    }

    private boolean isInBasket(Product product) {
        return productsQuantities.containsKey(product.getId());
    }

    public Integer getProductsCount() {
        return products.size();
    }

    public List<BasketItem> getBasketItems() {
        return productsQuantities.entrySet()
                .stream()
                .map(es -> new BasketItem(es.getKey(), es.getValue()))
                .collect(Collectors.toList());
    }

    public void remove(String id) {

    }
}
