package pl.arkani.LZ_2022301_LX.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.WeatherFinalClass01;
import pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days.WeatherData2;
import pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects.Location;
import pl.arkani.LZ_2022301_LX.webclient.weather.GeoLocationClient;
import pl.arkani.LZ_2022301_LX.webclient.weather.WeatherClient;

import java.util.List;

/*
  API
   * url connection
   * httpClient
   *resttempalte (client http wbudowany w Spring)

*/

//todo: cache'owanie (8 odc sprinboot - od nullpointerexception :https://www.youtube.com/watch?v=lWv3uBLO2LU)

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherService {

    @Autowired
    private WeatherClient weatherClient;
    @Autowired
    private GeoLocationClient geoLocationClient;



    public WeatherFinalClass01 getWeather(float lat,float lon, String lang) {

        //String responce= weatherClient.getWeatherCurrentForecastForCoordinates();
        //String responce2= weatherClient.getWeatherHourlyForecastForCoordinates();
//
//        log.info("#getWeather:"+responce);
//        log.info("#getWeather:"+responce2);
        return  weatherClient.getWeatherCurrentForCoordinates(lat,lon,lang);


    }

    public WeatherData2 getWeatherForecast(float lat, float lon, String lang) {

        //String responce= weatherClient.getWeatherCurrentForecastForCoordinates();
        //String responce2= weatherClient.getWeatherHourlyForecastForCoordinates();
//
//        log.info("#getWeather:"+responce);
//        log.info("#getWeather:"+responce2);
        return  weatherClient.getWeatherCurrentForecastForCoordinates(lat,lon,lang);


    }

    public List<Object> getObjectList() {
        return weatherClient.testRestTemplateRun();
    }


    public List<Location> getLocationFromCityName(String city,int limit) {
        return geoLocationClient.getLocationFromCityName(city,limit);
    }


}
