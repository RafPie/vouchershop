package pl.rpietrzak.vouchershop.catalog;


import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class JdbcProductStorage implements ProductStorage {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO `products_catalog__products` " +
                        "(`id`, `description`, `picture`, `price`) values " +
                        "(?,?,?,?)",
                product.getId(),
                product.getDescription(),
                product.getPicture(),
                product.getPrice()
        );
    }

    @Override
    public boolean isExists(String productId) {
        int productsCount = jdbcTemplate.queryForObject(
                "Select count(*) from `products_catalog__products` where id = ?",
                new Object[]{productId},
                Integer.class
        );

        return productsCount > 0;
    }

    @Override
    public Optional<Product> load(String productId) {
        return null;
    }

    @Override
    public List<Product> allProducts() {
        return null;
    }

    @Override
    public void clean() {
        jdbcTemplate.execute("delete form `products_catalog__products`");
    }
}
