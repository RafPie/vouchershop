package pl.rpietrzak.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.rpietrzak.payu.http.JavaHttpPayUApiClient;
import pl.rpietrzak.payu.PayU;
import pl.rpietrzak.payu.PayUCredentials;
import pl.rpietrzak.voucherstore.productcatalog.ProductCatalogFacade;
import pl.rpietrzak.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.rpietrzak.voucherstore.sales.offer.OfferMaker;
import pl.rpietrzak.voucherstore.sales.ordering.ReservationRepository;
import pl.rpietrzak.voucherstore.sales.payment.PayUPaymentGateway;
import pl.rpietrzak.voucherstore.sales.payment.PaymentGateway;
import pl.rpietrzak.voucherstore.sales.product.ProductCatalogProductDetailsProvider;
import pl.rpietrzak.voucherstore.sales.product.ProductDetailsProvider;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        return new SalesFacade(
                productCatalogFacade,
                new InMemoryBasketStorage(),
                () -> "customer_1",
                (productId) -> true,
                offerMaker,
                paymentGateway,
                reservationRepository);
    }

    @Bean
    PaymentGateway payUPaymentGateway() {
        return new PayUPaymentGateway(new PayU(
                PayUCredentials.productionOfEnv(),
                new JavaHttpPayUApiClient()
        ));
    }

    @Bean
    OfferMaker offerMaker(ProductDetailsProvider productDetailsProvider) {
        return new OfferMaker(productDetailsProvider);
    }

    @Bean
    ProductDetailsProvider productDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        return new ProductCatalogProductDetailsProvider(productCatalogFacade);
    }
}
