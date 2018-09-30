package main.java;

import main.java.entities.CryptoCoin;
import org.json.JSONException;

public class main {
    public static void main(String[] args) throws JSONException  {
        CryptoCoin ripple = new CryptoCoin("XRP");
        String exchange = "Kraken";
        String currency = "USD";
        Double price = ripple.getCurrentPrice(exchange, currency);
        System.out.println(price);
        Double price2 = ripple.getPriceFromDateTime("29-09-2018 00:00:00", exchange, currency);
        System.out.println(price2);
    }
}
