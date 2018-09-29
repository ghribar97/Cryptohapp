package main.java;

import main.java.entities.CryptoCoin;
import org.json.JSONException;

public class main {
    public static void main(String[] args) throws JSONException  {
        CryptoCoin ripple = new CryptoCoin("XRP");
        Double price = ripple.getCurrentPrice("Kraken", "USD");
        System.out.println(price);
        Double price2 = ripple.getPriceFromDateTime("01-01-2018 00:00:00", "Kraken", "USD");
        System.out.println(price2);
    }
}
