package pl.rpietrzak.vouchershop.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CatalogController {
    private final ProductCatalog catalog;

    public CatalogController(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/api/products")
    public List<Product> allProducts() {
        return catalog.allPublished();
    }

    @GetMapping("/api/names")
    public List<String> allNames() {
        return Arrays.asList("Jakub", "Michal");
    }
}
