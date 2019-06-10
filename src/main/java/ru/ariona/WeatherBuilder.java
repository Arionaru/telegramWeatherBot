package ru.ariona;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherBuilder {

    private static final String API_CALL_TEMPLATE =
            "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY_TEMPLATE = "&units=metric&appid=e770a66ca921832a3e974dd9412ab76b";


    private static JSONObject getJson(String city) {
        String urlString = API_CALL_TEMPLATE + city + API_KEY_TEMPLATE;
        URL urlObject = null;
        try {
            urlObject = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        int responseCode = 0;
        try {
            connection = (HttpURLConnection) urlObject.openConnection();

            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (responseCode == 404) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(response.toString());
    }

    public static SimpleWeather getWeather(String city) {

        JSONObject allWeather = getJson(city);

        int temp = allWeather.getJSONObject("main").getInt("temp");

        JSONArray weatherArray = allWeather.getJSONArray("weather");
        String description = weatherArray.getJSONObject(0).getString("description");

        return new SimpleWeather(temp,description);
    }

}
