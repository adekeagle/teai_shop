package pl.adcom.teai_shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.adcom.teai_shop.service.ProductService;

import java.math.BigDecimal;

@Component
@Profile("Start")
public class ShopVariantStart {

    Logger logger = LoggerFactory.getLogger(ShopVariantStart.class);

    private ProductService productService;

    @Autowired
    public ShopVariantStart(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo(){

        logger.info("Variant Start");
        logger.info("Wartość netto w koszyku: " + productService.totalPrice() + " zł");
        logger.info("Dodaję 1 produkt do koszyka w cenie 50.00 zł netto");
        productService.addProductToBasket("tusz", new BigDecimal(50.0));
        logger.info("Produkt został dodany");

        BigDecimal total = productService.totalPrice();

        logger.info("Wartość netto koszyka wynosi: " + total + " zł");
    }



}
