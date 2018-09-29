package main.java.entities;

public interface StockAsset {
    double getCurrentPrice(String exchange, String currency);
    double getPriceFromDateTime(String date, String exchange, String currency);
    String getName();
}
