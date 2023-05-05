package pl.arkani.LZ_2022301_LX.webclient.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.arkani.LZ_2022301_LX.model.WeatherJsonObjects.WeatherData;

// to jest inna metoda, bardziej transparentna - korzysta z obiektów zdefiniowanych
// w pl.arkani.LZ_2022301_LX.model.WeatherJsonObjects -> głowny obiekt to WeatherData
public class JsonToObjectExample {
    public static void main(String[] args) {
        String json = "{\"coord\":{\"lon\":18.3428,\"lat\":54.4057},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"bezchmurnie\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":10.74,\"feels_like\":9.18,\"temp_min\":9.43,\"temp_max\":10.74,\"pressure\":1026,\"humidity\":50},\"visibility\":10000,\"wind\":{\"speed\":2.57,\"deg\":310},\"clouds\":{\"all\":0},\"dt\":1683194350,\"sys\":{\"type\":1,\"id\":1696,\"country\":\"PL\",\"sunrise\":1683169400,\"sunset\":1683224607},\"timezone\":7200,\"id\":3103882,\"name\":\"Banino\",\"cod\":200}";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            WeatherData weatherData = objectMapper.readValue(json, WeatherData.class);
            System.out.println(weatherData.toString());

            long epochDate = weatherData.getDt();
            String formattedDt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date (epochDate*1000));

            System.out.println(formattedDt);
            System.out.println(weatherData.getMain().getTemp_max());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}