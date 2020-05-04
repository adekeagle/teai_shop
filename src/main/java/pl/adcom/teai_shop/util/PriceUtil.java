package pl.adcom.teai_shop.util;

import java.math.BigDecimal;
import java.util.Random;

public class PriceUtil {

    public static Double randomPrice(){
        Random random = new Random();
        return random.nextDouble() * 250 + 50;
    }
}
