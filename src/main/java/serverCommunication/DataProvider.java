package main.java.serverCommunication;

import main.java.entities.StockAsset;
import org.json.JSONException;

/**
 * Singleton class for providing data from the server.
 */
public class DataProvider {
    private static DataProvider ourInstance = new DataProvider();
    private ServerConnector serverConnector;

    public static DataProvider getInstance() {
        return ourInstance;
    }

    private DataProvider() {
        this.serverConnector = new ServerConnector();
    }

    public double getStockAssetPrice(StockAsset asset, String exchange, String currency, long timestamp) {
        try {
            String url = String.format("price?fsym=%s&tsyms=%s&e=%s&ts=%d", asset.getName(), currency, exchange, timestamp);
            System.out.println(url);
            String price = this.serverConnector.getData(url).get(currency).toString();
            return Double.parseDouble(price);
        } catch (JSONException j) {
            System.out.println(j.toString());
            return -1;
        }
    }
}
