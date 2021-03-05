package pl.rpietrzak.vouchershop.catalog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductCatalogHttpTest {
    @LocalServerPort
    int port;

    @Autowired
    ProductCatalog productCatalog;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Before
    public void emptyCatalog() {
        productCatalog.clean();
    }
    @Test
    public void itShowsAllPublishedProducts() {
        //Arrange
        thereIsDraftProduct("draft product");
        thereIsReadyToSellProduct("product 1");
        thereIsReadyToSellProduct("product 2");
        //Act
        var url = String.format("http://localhost:%s/api/products", port);
        ResponseEntity<Product[]> response =
                testRestTemplate.getForEntity(url, Product[].class);
        //Assert
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        assertThat(response.getBody())
                .hasSize(2)
                .extracting(Product::getDescription)
                .contains("product 1", "product 2")
                .doesNotContain("draft product")
        ;

    }

    private void thereIsReadyToSellProduct(String name) {
        var prodId = productCatalog.registerProduct();
        productCatalog.updateDetails(prodId, name, name);
        productCatalog.applyPrice(prodId, BigDecimal.valueOf(20.20));
    }

    private void thereIsDraftProduct(String name) {
        var prodId = productCatalog.registerProduct();
        productCatalog.updateDetails(prodId, name, name);
    }
}
