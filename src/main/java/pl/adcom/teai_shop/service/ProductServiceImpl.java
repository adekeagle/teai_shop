package pl.adcom.teai_shop.service;

import org.springframework.stereotype.Service;
import pl.adcom.teai_shop.model.Product;
import pl.adcom.teai_shop.util.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    List<Product> productList;

    public ProductServiceImpl() {
        productList = new ArrayList<>();
        productList.add(new Product("Toner", new BigDecimal(Price.randomPrice())));
        productList.add(new Product("Drukarka Canon", new BigDecimal(Price.randomPrice())));
        productList.add(new Product("Przewód USB", new BigDecimal(Price.randomPrice())));
        productList.add(new Product("Papier", new BigDecimal(Price.randomPrice())));
        productList.add(new Product("Drukarka HP", new BigDecimal(Price.randomPrice())));
    }

    @Override
    public void addProductToBasket(String name, BigDecimal price) {
        productList.add(new Product(name, price));
    }

    @Override
    public BigDecimal totalPrice() {
        BigDecimal result = new BigDecimal(0.00);

        for (Product product: productList) {
            result = result.add(product.getPrice());
        }
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "ProductServiceImpl{" +
                "productList=" + productList +
                '}';
    }
}
