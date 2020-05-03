package pl.adcom.teai_shop.possibility;

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
public class Plus {

    @Value("${info.tax}")
    private BigDecimal tax;

    private ProductService productService;

    @Autowired
    public Plus(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo(){
        System.out.println("Variant Plus");
        System.out.println("Wartość netto w koszyku: " + productService.totalPrice() + " zł");
        System.out.println("Dodaję 1 produkt do koszyka w cenie 80.00 zł netto");
        productService.addProductToBasket("Akcesoria do drukarki", new BigDecimal(80.0));
        System.out.println("Produkt został dodany");
        System.out.println("Wartość netto koszyka wynosi: " + productService.totalPrice() + " zł");
        System.out.println("Wartość brutto koszyka wynosi: " + getPriceWithVat(productService.totalPrice()) + " zł");
    }

    private BigDecimal getPriceWithVat(BigDecimal price){
        return price.multiply(tax.divide(new BigDecimal(100)).add(new BigDecimal(1)));
    }
}
