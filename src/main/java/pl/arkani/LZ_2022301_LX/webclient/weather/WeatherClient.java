package pl.arkani.LZ_2022301_LX.webclient.weather;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.arkani.LZ_2022301_LX.dto.OpenWeatherJsonDto;
import pl.arkani.LZ_2022301_LX.model.WeatherFinalClass01;

import java.util.List;

@Component
public class WeatherClient {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "a66b540bcde954f8ef6423bf15c6ee5c";

    //String lat = "54.405665044";
    //String lon = "18.342831962";
   // String lang ="pl";

    //https://api.openweathermap.org/data/2.5/weather?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metric&lang=pl
    public WeatherFinalClass01 getWeatherCurrentForecastForCoordinates(float lat,float lon,String lang)
            //(String url,String lat,String lon)
    //(String ApiKey,String lat,String lon,String lang)
     {

         OpenWeatherJsonDto openWeatherDto = getWeatherUnivForecastForCoordinates( "weather?lat={lan}&lon={lon}&appid={apiKey}&units=metric&lang={lang}",
                 OpenWeatherJsonDto.class,//String.class, // podmienic docelowo na mapowany obiekt
                 lat, lon,API_KEY,lang);
         return mapToWeather(openWeatherDto);
    }

    private static WeatherFinalClass01 mapToWeather(OpenWeatherJsonDto openWeatherDto) {
        return WeatherFinalClass01.builder()
                .temperature(openWeatherDto.getMain().getTemp())
                .temperatureMin(openWeatherDto.getMain().getTemp_min())
                .temperatureMax(openWeatherDto.getMain().getTemp_max())
                .feelsLike(openWeatherDto.getMain().getFeels_like())
                .pressure(openWeatherDto.getMain().getPressure())
                .humidity(openWeatherDto.getMain().getHumidity())
                .windSpeed(openWeatherDto.getWind().getSpeed())
                .main(openWeatherDto.getWeather().get(0).getMain())
                .description(openWeatherDto.getWeather().get(0).getDescription())
                .nearestCity(openWeatherDto.getName())
                .latitude(openWeatherDto.getCoord().getLat())
                .longitude(openWeatherDto.getCoord().getLon())
                .country(openWeatherDto.getSys().getCountry())
                .dt(openWeatherDto.getDt())
                .build();
    }

    //mozna exlude=minutely,hourly
    //https://api.openweathermap.org/data/2.5/forecast?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metric&lang=pl
    public String getWeatherHourlyForecastForCoordinates(float lat,float lon,String lang)//(String lat,String lon,String lang)
    {
        return getWeatherUnivForecastForCoordinates( "forecast?lat={lan}&lon={lon}&appid={apiKey}&units=metric&lang={lang}",
                String.class, // podmienic docelowo na mapowany obiekt
                lat, lon,API_KEY,lang);
    }


    private <T> T getWeatherUnivForecastForCoordinates(String url, final Class<T> responseObject, Object...objects) {
        return restTemplate.getForObject(WEATHER_URL + url, responseObject,objects);



    }

public   List<Object> testRestTemplateRun(){
        return testRestTemplate();// (lat, lon,API_KEY,lang);
}
    private List<Object> testRestTemplate() // (String lat,String lon,String apiKey, String lang)
     {
    //String url = "http://example.com/api/objects";
    String url = "https://api.openweathermap.org/data/2.5/weather?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metric&lang=pl";
    //String url = "forecast?lat="+lat+"&lon="+lon+"&appid="+apiKey+"&units=metric&lang=pl";
   // RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<List<Object>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Object>>() {
            });
    List<Object> resultObjects = response.getBody();
    System.out.println("#objects:"+resultObjects);
    return resultObjects;
}

}
