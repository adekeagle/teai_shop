package pl.adcom.teai_shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.adcom.teai_shop.service.ProductService;

import java.math.BigDecimal;

@Component
@Profile("Plus")
public class ShopVariantPlus {

    Logger logger = LoggerFactory.getLogger(ShopVariantPlus.class);

    @Value("${info.tax}")
    private BigDecimal tax;

    private ProductService productService;

    @Autowired
    public ShopVariantPlus(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo(){
        logger.info("Variant Plus");
        logger.info("Wartość netto w koszyku: " + productService.totalPrice() + " zł");
        logger.info("Dodaję 1 produkt do koszyka w cenie 80.00 zł netto");

        productService.addProductToBasket("Akcesoria do drukarki", new BigDecimal(80.0));

        logger.info("Produkt został dodany");
        logger.info("Wartość netto koszyka wynosi: " + productService.totalPrice() + " zł");
        logger.info("Wartość brutto koszyka wynosi: " + getPriceWithVat(productService.totalPrice()) + " zł");
    }

    private BigDecimal getPriceWithVat(BigDecimal price){
        return price.multiply(tax.divide(new BigDecimal(100)).add(new BigDecimal(1)));
    }
}
