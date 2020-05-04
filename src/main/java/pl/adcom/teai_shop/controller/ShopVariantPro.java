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
import java.math.RoundingMode;

@Component
@Profile("Pro")
public class ShopVariantPro {

    Logger logger = LoggerFactory.getLogger(ShopVariantPlus.class);

    @Value("${info.tax}")
    private BigDecimal tax;

    @Value("${info.discount}")
    private BigDecimal discount;

    private ProductService productService;

    @Autowired
    public ShopVariantPro(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo(){
        logger.info("Variant Pro");
        logger.info("Wartość netto w koszyku: " + productService.totalPrice() + " zł");
        logger.info("Wartość brutto po rabacie " + discount + "% wynosi: " + getPriceWithVatIncludeDiscount(productService.totalPrice()) + " zł");
    }


    private BigDecimal getPriceWithVatIncludeDiscount(BigDecimal price){
        BigDecimal priceWithVat = price.multiply(tax.divide(new BigDecimal(100)).add(new BigDecimal(1)));
        logger.info("Wartość brutto koszyka: " + priceWithVat.setScale(2, RoundingMode.HALF_UP) + " zł");
        return priceWithVat.subtract(priceWithVat.multiply(discount.divide(new BigDecimal(100)))).setScale(2, RoundingMode.HALF_UP);
    }
}
