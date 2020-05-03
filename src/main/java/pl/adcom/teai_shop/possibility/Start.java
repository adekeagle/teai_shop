package pl.adcom.teai_shop.possibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.adcom.teai_shop.service.ProductService;
import pl.adcom.teai_shop.util.Price;

import java.math.BigDecimal;

@Component
@Profile("Start")
public class Start {

    private ProductService productService;

    @Autowired
    public Start(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getInfo(){
        System.out.println("Variant Start");
        System.out.println("Wartość netto w koszyku: " + productService.totalPrice() + " zł");
        System.out.println("Dodaję 1 produkt do koszyka w cenie 50.00 zł netto");
        productService.addProductToBasket("tusz", new BigDecimal(50.0));
        System.out.println("Produkt został dodany");
        BigDecimal total = productService.totalPrice();

        System.out.println("Wartość netto koszyka wynosi: " + total + " zł");
    }



}
