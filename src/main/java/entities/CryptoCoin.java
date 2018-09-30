package main.java.entities;

import main.java.common.DateManager;
import main.java.serverCommunication.DataProvider;

public class CryptoCoin implements StockAsset {
    private final String name;
    private final DataProvider dataProvider;

    public CryptoCoin(String name) {
        this.name = name;
        this.dataProvider = DataProvider.getInstance();
    }

    @Override
    public double getCurrentPrice(String exchange, String currency) {
        return this.dataProvider.getCurrentStockAssetPrice(this, exchange, currency);
    }

    @Override
    public double getPriceFromDateTime(String date, String exchange, String currency) {
        long date2 = DateManager.getTimestampFromDate(date);
        return this.dataProvider.getStockAssetPriceFromTimestamp(this, exchange, currency, date2);
    }

    @Override
    public String getName() {
        return name;
    }
}
