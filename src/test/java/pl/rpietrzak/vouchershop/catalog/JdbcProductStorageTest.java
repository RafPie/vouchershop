package pl.rpietrzak.vouchershop.catalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate.execute("DROP TABLE `products_catalog__products` IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `products_catalog__products` (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "`picture` varchar(255)," +
                "`price` DECIMAL(12,2)," +
                "PRIMARY KEY(id)" +
                ");");
    }

    @Test
    public void itAllowAddProduct() {
        JdbcProductStorage productStorage = new JdbcProductStorage(jdbcTemplate);
        Product product = ProductFixtures.randomProduct();

        productStorage.save(product);

        assertThat(productStorage.isExists(product.getId()))
                .isTrue();

        assertThat(productStorage.isExists(UUID.randomUUID().toString()))
                .isFalse();
    }

    @Test
    public void itAllowLoadAllProducts() {

    }

    @Test
    public void itAllowCheckIfProductExists() {

    }
}
