package pl.arkani.LZ_2022301_LX.Examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days.WeatherData2;
import pl.arkani.LZ_2022301_LX.model.WeatherJsonObjects.WeatherData;
//to nie działa
public class JsonToObjectExampleFor4Days {
    public static void main(String[] args) throws Exception {
        String json = "{\"cod\":\"200\",\"message\":0,\"cnt\":40,\"list\":[{\"dt\":1683201600,\"main\":{\"temp\":12.74,\"feels_like\":10.93,\"temp_min\":12.29,\"temp_max\":12.74,\"pressure\":1025,\"sea_level\":1025,\"grnd_level\":1004,\"humidity\":33,\"temp_kf\":0.45},\"weather\":[{\"id\":801,\"main\":\"Clouds\",\"description\":\"pochmurnie\",\"icon\":\"02d\"}],\"clouds\":{\"all\":20},\"wind\":{\"speed\":2.64,\"deg\":12,\"gust\":2.54},\"visibility\":10000,\"pop\":0,\"sys\":{\"pod\":\"d\"},\"dt_txt\":\"2023-05-04 12:00:00\"}],\"city\":{\"id\":3103882,\"name\":\"Banino\",\"coord\":{\"lat\":54.4057,\"lon\":18.3428},\"country\":\"PL\",\"population\":2593,\"timezone\":7200,\"sunrise\":1683169400,\"sunset\":1683224607}}";
        
        ObjectMapper mapper = new ObjectMapper();
        WeatherData2 weatherData2 = mapper.readValue(json, WeatherData2.class);
        
        // Access the parsed data
        System.out.println(weatherData2.getCity().getName());
       // System.out.println(weatherData2.getWeatherInfo2List().get(0).getTemp());
    }
}