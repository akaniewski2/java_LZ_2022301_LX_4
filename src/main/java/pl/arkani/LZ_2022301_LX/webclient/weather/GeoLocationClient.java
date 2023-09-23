package pl.arkani.LZ_2022301_LX.webclient.weather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects.Location;
import pl.arkani.LZ_2022301_LX.webclient.weather.WeatherClient;

import java.util.List;
@Component
public class GeoLocationClient {
    private static final String OPEN_WEATHER_MAIN_URL = WeatherClient.OPEN_WEATHER_MAIN_URL;
    private static final String GEO_URL =WeatherClient.GEO_URL;
    private static final String API_KEY = WeatherClient.API_KEY;

    private static final int LIMIT = 5;
    private static final String CITY = "pruszcz";


    public List<Location> getLocationFromCityName(String city,int limit) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = OPEN_WEATHER_MAIN_URL + GEO_URL;

        // Replace placeholders with actual values
        String url = apiUrl.replace("{city}", city)
                .replace("{limit}", String.valueOf(limit))
                .replace("{apiKey}", API_KEY);

        List<Location> locations = getObjectFromJson(restTemplate, url, new TypeReference<List<Location>>() {});
        return locations;
    }



    private /*static*/ <T> T getObjectFromJson(RestTemplate restTemplate, String url, TypeReference<T> responseType) {
        String jsonResponse = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonResponse, responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

//Static Contex --------------------------------------------------------------------------------------------
//public static void main(String[] args) {
//    RestTemplate restTemplate = new RestTemplate();
//    String apiUrl = OPEN_WEATHER_MAIN_URL + GEO_URL;
//
//    // Replace placeholders with actual values
//    String url = apiUrl.replace("{city}", CITY)
//            .replace("{limit}", String.valueOf(LIMIT))
//            .replace("{apiKey}", API_KEY);
//
//    // Call the method to retrieve the JSON
//    testRun(restTemplate, url);
//}
//
//    private static void testRun(RestTemplate restTemplate, String url) {
//        List<Location> locations = getObjectFromJson(restTemplate, url, new TypeReference<List<Location>>() {});
//
//        for (Location location : locations) {
//            System.out.println(location);
//        }
//
//        System.out.println("--------------------------------------------");
//        System.out.println(locations);
//    }
//


}