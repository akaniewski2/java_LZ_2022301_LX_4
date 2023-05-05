package pl.arkani.LZ_2022301_LX.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.arkani.LZ_2022301_LX.model.WeatherFinalClass01;
import pl.arkani.LZ_2022301_LX.service.WeatherService;

import java.util.List;


/*
YT: https://www.youtube.com/watch?v=DPFYyjyeuVA&t=643s
* https://home.openweathermap.org
* pass=ApiKeyForArkani
* https://api.openweathermap.org/data/2.5/weather?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metrics&lang=pl
* https://openweathermap.org/api/geocoding-api
* get maual coordinates : https://latitude.to/map/pl/poland/cities/chwaszczyno/articles/416282/nowe-tokary
* */


/*tabela html z filtrami iinymi dodatkami :https://datatables.net/*/
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/test/weather")
public class WeatherRestController {

    private final WeatherService weatherService;

    @GetMapping("/json")
    public WeatherFinalClass01 getWeatherJson() {
        WeatherFinalClass01 weather = new WeatherFinalClass01();
      //  weather.setLat(54.405665044F);
      //  weather.setLon(18.342831962F);

        //warszawa
        weather.setLatitude(52.229675F);
        weather.setLongitude(21.012230F);

        weather.setLang("pl");

        weatherService.getObjectList();
             return weatherService.getWeather(weather.getLatitude(),weather.getLongitude(),weather.getLang());
    }

    @GetMapping("/json/objects")
    public  List<Object>  getWeatherJsonObjects() {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metric&lang=pl";
        //String url = "forecast?lat="+lat+"&lon="+lon+"&appid="+apiKey+"&units=metric&lang=pl";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Object>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Object>>() {
                });
        List<Object> resultObjects = response.getBody();
        System.out.println("#objects:" + resultObjects);
        return   resultObjects;
    }



}
