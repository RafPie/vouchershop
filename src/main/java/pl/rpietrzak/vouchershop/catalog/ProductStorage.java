package pl.rpietrzak.vouchershop.catalog;

import java.util.List;
import java.util.Optional;

public interface ProductStorage {
    void save(Product newProduct);

    boolean isExists(String productId);

    Optional<Product> load(String productId);

    List<Product> allProducts();

    void clean();
}
