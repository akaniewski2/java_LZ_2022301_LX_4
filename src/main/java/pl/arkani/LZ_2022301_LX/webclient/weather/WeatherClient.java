package pl.arkani.LZ_2022301_LX.webclient.weather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.arkani.LZ_2022301_LX.model.OpenWeatherJsonDto.OpenWeatherJsonDto;
import pl.arkani.LZ_2022301_LX.model.WeatherFinalClass01;
import pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days.WeatherData2;
import pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects.Location;

import java.util.List;

@Component
public class WeatherClient {

    private RestTemplate restTemplate = new RestTemplate();
    public static final String OPEN_WEATHER_MAIN_URL = "https://api.openweathermap.org/";
    public static final String CURRENT_WEATHER_URL = "data/2.5/weather?lat={lan}&lon={lon}&appid={apiKey}&units=metric&lang={lang}";
    public static final String GEO_URL = "geo/1.0/direct?q={city}&limit={limit}&appid={apiKey}";
    public static final String API_KEY = "a66b540bcde954f8ef6423bf15c6ee5c";

    //String lat = "54.405665044";
    //String lon = "18.342831962";
   // String lang ="pl";


    //https://api.openweathermap.org/data/2.5/weather?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metric&lang=pl

    public WeatherFinalClass01 getWeatherCurrentForCoordinates(float lat, float lon, String lang)
            //(String url,String lat,String lon)
    //(String ApiKey,String lat,String lon,String lang)
     {

         OpenWeatherJsonDto openWeatherDto = getObjectFromJson(CURRENT_WEATHER_URL,
                 OpenWeatherJsonDto.class,//String.class, // podmienic docelowo na mapowany obiekt
                 lat, lon,API_KEY,lang);
    //     testRestTemplate3();//todo:testowe wywołanie

//         getObjectFromJson2(OPEN_WEATHER_MAIN_URL + GEO_URL,
//                 String.class, "London", 5, API_KEY);

         return mapToWeather(openWeatherDto);
    }


    public WeatherData2 getWeatherCurrentForecastForCoordinates(float lat, float lon, String lang)
    //(String url,String lat,String lon)
    //(String ApiKey,String lat,String lon,String lang)
    {

        OpenWeatherJsonDto openWeatherDto = getObjectFromJson(CURRENT_WEATHER_URL,
                OpenWeatherJsonDto.class,//String.class, // podmienic docelowo na mapowany obiekt
                lat, lon,API_KEY,lang);
        //     testRestTemplate3();//todo:testowe wywołanie

//         getObjectFromJson2(OPEN_WEATHER_MAIN_URL + GEO_URL,
//                 String.class, "London", 5, API_KEY);
        WeatherData2 weatherHourlyForecastForCoordinates = getWeatherHourlyForecastForCoordinates(	lat, lon, API_KEY,"pl");
        System.out.println("#weatherHourlyForecastForCoordinates:"+weatherHourlyForecastForCoordinates);

        return weatherHourlyForecastForCoordinates
                ;
    }

    public static WeatherFinalClass01 mapToWeather(WeatherData2 dto,int idx) {
        return WeatherFinalClass01.builder()
                .temperature((float) dto.getList().get(idx).getMain().getTemp())
                .temperatureMin((float) dto.getList().get(idx).getMain().getTemp_min())
                .temperatureMax((float) dto.getList().get(idx).getMain().getTemp_max())
                .feelsLike((float) dto.getList().get(idx).getMain().getFeels_like())
                .pressure(dto.getList().get(idx).getMain().getPressure())
                .humidity(dto.getList().get(idx).getMain().getHumidity())
                .windSpeed((float) dto.getList().get(idx).getWind().getSpeed())
                .main(dto.getList().get(idx).getWeather().get(0).getMain())
                .description(dto.getList().get(idx).getWeather().get(0).getDescription())
                .nearestCity(dto.getCity().getName())
                .latitude((float) dto.getCity().getCoord().getLat())
                .longitude((float) dto.getCity().getCoord().getLon())
                .country(dto.getCity().getCountry())
                .dt(dto.getList().get(idx).getDt())
                .sunrise(dto.getCity().getSunrise())
                .sunset(dto.getCity().getSunset())
                .build();
    }


    private static WeatherFinalClass01 mapToWeather(OpenWeatherJsonDto dto) {
        return WeatherFinalClass01.builder()
                .temperature(dto.getMain().getTemp())
                .temperatureMin(dto.getMain().getTemp_min())
                .temperatureMax(dto.getMain().getTemp_max())
                .feelsLike(dto.getMain().getFeels_like())
                .pressure(dto.getMain().getPressure())
                .humidity(dto.getMain().getHumidity())
                .windSpeed(dto.getWind().getSpeed())
                .main(dto.getWeather().get(0).getMain())
                .description(dto.getWeather().get(0).getDescription())
                .nearestCity(dto.getName())
                .latitude(dto.getCoord().getLat())
                .longitude(dto.getCoord().getLon())
                .country(dto.getSys().getCountry())
                .dt(dto.getDt())
                .sunrise(dto.getSys().getSunrise())
                .sunset(dto.getSys().getSunset())
                .build();
    }

    //mozna exlude=minutely,hourly
    //https://api.openweathermap.org/data/2.5/forecast?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metric&lang=pl
    public String getWeatherHourlyForecastForCoordinatesTest(float lat,float lon,String lang)//(String lat,String lon,String lang)
    {
        return getObjectFromJson( "data/2.5/forecast?lat={lan}&lon={lon}&appid={apiKey}&units=metric&lang={lang}",
                String.class, // podmienic docelowo na mapowany obiekt
                lat, lon,API_KEY,lang);
    }

    public WeatherData2 getWeatherHourlyForecastForCoordinates(float lat, float lon ,String apiKey,String  lang)//(String lat,String lon,String lang) //,
    {

        String url ="data/2.5/forecast?lat={lat}&lon={lon}&appid={apiKey}&units=metric&lang={lang}";

       url=url.replace("{lon}",String.valueOf(lon))
              .replace("{lat}",String.valueOf(lat))
              .replace("{apiKey}",apiKey)
              .replace("{lang}",lang);


       System.out.println("#url"+url);

        //return getObjectFromJson( "data/2.5/forecast?lat={lan}&lon={lon}&appid={apiKey}&units=metric&lang={lang}",
        return getObjectFromJson(url ,
                WeatherData2.class, // podmienic docelowo na mapowany obiekt
                lat, lon,API_KEY,lang);//
    }

    private <T> T getObjectFromJson(String url, final Class<T> responseObject, Object...objects) {
    return   restTemplate.getForObject(OPEN_WEATHER_MAIN_URL + url, responseObject,objects);

    }

    private static <T> T getObjectFromJson2(RestTemplate restTemplate, String url, TypeReference<T> responseType, Object...objects) {
        String jsonResponse = restTemplate.getForObject(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonResponse, responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    
    //getObjectFromJson(GEO_URL,String.class,"London",5,API_KEY);

    //ta metoda nie dziala jesli JSON zaczyna sie od znaku tablie np: [{adssda},{asdsada}]
    public Location testRestTemplate3()
    //(String ApiKey,String lat,String lon,String lang)
    {
        Location geoLocation = getObjectFromJson(OPEN_WEATHER_MAIN_URL + GEO_URL,
                Location.class, "London", 1, API_KEY);

        System.out.println("#city:"+geoLocation);
        return geoLocation;
    }

    public String testRestTemplate2()
    //(String ApiKey,String lat,String lon,String lang)
    {
        String city = restTemplate.getForObject(OPEN_WEATHER_MAIN_URL + GEO_URL,
                String.class, "London", 5, API_KEY);

        System.out.println("#city:"+city);
        return city;
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
