package pl.rpietrzak.vouchershop.catalog;

import pl.rpietrzak.vouchershop.catalog.exceptions.NoSuchProductException;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductCatalog {
    private final ProductStorage products;

    public ProductCatalog(ProductStorage productStorage) {
        this.products = productStorage;
    }

    public String registerProduct() {
        Product newProduct = new Product(UUID.randomUUID());
        products.save(newProduct);
        return newProduct.getId();
    }

    public boolean isExists(String productId) {
        return  products.isExists(productId);
    }

    public Product load(String productId) {
        return getProductOrThrow(productId);
    }

    public void updateDetails(String productId, String productDesc, String productPicture) {
        Product loaded = getProductOrThrow(productId);
        loaded.setDescription(productDesc);
        loaded.setPicture(productPicture);
    }

    public void applyPrice(String productId, BigDecimal price) {
        Product loaded = getProductOrThrow(productId);
        loaded.setPrice(price);
    }

    public List<Product> allPublished() {
        return products.allProducts()
                .stream()
                .filter(p -> p.getDescription() != null)
                .filter(p -> p.getPicture() != null)
                .filter(p -> p.getPrice() != null)
                .collect(Collectors.toList());
    }

    private Product getProductOrThrow(String productId) {
        return products.load(productId)
                .orElseThrow(() -> new NoSuchProductException(String.format("There is no product with id %s", productId)));
    }

    public void clean() {
        products.clean();
    }
}
