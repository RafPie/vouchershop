package pl.rpietrzak.vouchershop.catalog;

import org.junit.Assert;
import org.junit.Test;
import pl.rpietrzak.vouchershop.catalog.exceptions.NoSuchProductException;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class ListProductStorageTest {

    @Test
    public void itAllowAddProduct() {
        //Arrange
        ProductStorage productStorage = new ListProductStorage();
        Product product = ProductFixtures.randomProduct();
        //Act
        productStorage.save(product);
        //Assert
        Assert.assertTrue(productStorage.isExists(product.getId()));
    }

    @Test
    public void itAllowLoadAllProducts() {
        //Arrange
        ProductStorage productStorage = new ListProductStorage();
        var product1 = ProductFixtures.randomProduct();
        var product2 = ProductFixtures.randomProduct();

        //Act
        productStorage.save(product1);
        productStorage.save(product2);

        //Assert
        List<Product> all = productStorage.allProducts();
        assertThat(all)
                .hasSize(2)
                .extracting(Product::getId)
                .contains(product1.getId())
                .contains(product2.getId())
        ;
    }

    @Test
    public void itAllowCheckIfProductExists() {
        ProductStorage productStorage = new ListProductStorage();
        var product1 = ProductFixtures.randomProduct();

        productStorage.save(product1);

        assertThat(productStorage.isExists(product1.getId()))
                .isTrue();
        assertThat(productStorage.isExists(UUID.randomUUID().toString()))
                .isFalse();
    }

    @Test
    public void itAllowLoadSingleProduct() {
        ProductStorage productStorage = new ListProductStorage();
        var product1 = ProductFixtures.randomProduct();

        productStorage.save(product1);

        var loaded = productStorage.load(product1.getId())
                .get();

        assertThat(loaded.getId())
                .isEqualTo(product1.getId());
    }

    @Test(expected = NoSuchProductException.class)
    public void itSholuldProtectFromDefenseProgramming() {
        ProductStorage productStorage = new ListProductStorage();

        var loaded = productStorage.load(UUID.randomUUID().toString())
                .orElseThrow(() -> new NoSuchProductException("no such product"));
    }

    @Test
    public void testIt() {
        assertThat("Ala ma kota").containsIgnoringCase("ala");
        assertThat(Arrays.asList("kuba", "michal", "artur"))
                .hasSize(3)
                .contains("kuba", "michal")
                .doesNotContain("pawel");
    }
}
