package pl.adcom.teai_shop.possibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.adcom.teai_shop.service.ProductService;
import pl.adcom.teai_shop.util.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Profile("Pro")
public class Pro {

    @Value("${info.tax}")
    private BigDecimal tax;

    @Value("${info.discount}")
    private BigDecimal discount;

    private ProductService productService;

    @Autowired
    public Pro(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo(){
        System.out.println("Variant Pro");
        System.out.println("Wartość netto w koszyku: " + productService.totalPrice() + " zł");
        System.out.println("Wartość brutto po rabacie " + discount + "% wynosi: " + getPriceWithVatIncludeDiscount(productService.totalPrice()) + " zł");
        System.out.println();
    }


    private BigDecimal getPriceWithVatIncludeDiscount(BigDecimal price){
        BigDecimal priceWithVat = price.multiply(tax.divide(new BigDecimal(100)).add(new BigDecimal(1)));
        System.out.println("Wartość brutto koszyka: " + priceWithVat.setScale(2, RoundingMode.HALF_UP) + " zł");
        return priceWithVat.subtract(priceWithVat.multiply(discount.divide(new BigDecimal(100)))).setScale(2, RoundingMode.HALF_UP);
    }
}
