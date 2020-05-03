package pl.adcom.teai_shop.service;

import pl.adcom.teai_shop.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void addProductToBasket(String name, BigDecimal price);
    BigDecimal totalPrice();
//    void Basket();
}
