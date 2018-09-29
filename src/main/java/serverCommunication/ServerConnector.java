package main.java.serverCommunication;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import org.json.*;
import java.io.*;

/**
 * class for connecting to CryptoCompare API
 */
public class ServerConnector {
    private final String CONNECTION_STRING = "https://min-api.cryptocompare.com/data/";
    private HttpsURLConnection connection;

    public ServerConnector() {
    }

    /**
     * Function returns the response of the server in JSON format
     * @param url url of resource
     * @return JSON data
     * @throws JSONException
     */
    public JSONObject getData(String url) throws JSONException {
        this.setupConnection(url);
        return this.stringToJSON(this.getResponse());
    }

    /**
     * Sets up a connection to a given url. Only method GET is used, since we are only interested in data.
     * @param url url of resource.
     */
    private void setupConnection(String url) {
        try {
            URL urlPath = new URL(CONNECTION_STRING + url);
            this.connection = (HttpsURLConnection) urlPath.openConnection();
            this.connection.setRequestMethod("GET");  // only 'get' method is needed
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Sends a request and wait for response.
     * @return String response
     */
    private String getResponse() {
        try {
            InputStream is = this.connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (this.connection != null) {
                this.connection.disconnect();
            }
        }
    }

    /**
     * Convert String into JSON
     * @param str String that needs to be converted into JSON.
     * @return JSON class
     * @throws JSONException
     */
    private JSONObject stringToJSON(String str) throws JSONException {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            System.out.println("Could not transform String: " + str + " into JSON!");
            System.out.println(e.toString());
            return new JSONObject("");   // empty json
        }
    }
}
