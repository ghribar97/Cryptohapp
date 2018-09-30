package main.java.serverCommunication;

import main.java.entities.StockAsset;
import org.json.JSONException;
import org.json.JSONObject;

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

    /**
     * Gets JSON response of the server.
     * @param url url of the resource.
     * @return JSON response
     * @throws JSONException if there is an error
     */
    public JSONObject getJsonResponse(String url) throws JSONException {
        JSONObject jo = this.serverConnector.getData(url);
        if (jo.has("Response") && jo.get("Response").equals("Error")) {
            throw new JSONException(jo.get("Message").toString());
        }
        else { return jo; }
    }

    /**
     * Get the current price of any cryptocurrency in any other currency that you need.
     * @param asset asset in which we are interested.
     * @param exchange prices from which exchange.
     * @param currency in which currency the result should be.
     * @return price
     */
    public double getCurrentStockAssetPrice(StockAsset asset, String exchange, String currency) {
        String url = String.format("price?fsym=%s&tsyms=%s&e=%s", asset.getName(), currency, exchange);
        try {
            JSONObject jo = this.getJsonResponse(url);
            return Double.parseDouble(jo.get(currency).toString());
        } catch (JSONException j) {
            System.out.println(j.toString());
            return -1;
        }
    }

    /**
     * Get the price of any cryptocurrency in any other currency that you need at a given timestamp.
     * The price comes from the daily info - so it would be the price at the end of the day GMT based on the requested TS.
     * @param asset asset in which we are interested.
     * @param exchange prices from which exchange.
     * @param currency in which currency the result should be.
     * @param timestamp timestamp
     * @return price
     */
    public double getStockAssetPriceFromTimestamp(StockAsset asset, String exchange, String currency, long timestamp) {
        String url = String.format("pricehistorical?fsym=%s&tsyms=%s&e=%s&ts=%d", asset.getName(), currency,
                    exchange, timestamp / 1000);  // to seconds
        try {
            JSONObject jo = this.getJsonResponse(url);
            return Double.parseDouble(jo.getJSONObject(asset.getName()).get(currency).toString());
        } catch (JSONException j) {
            System.out.println(j.toString());
            return -1;
        }
    }
}
