package pl.arkani.LZ_2022301_LX.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.*;
import pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days.WeatherData2;
import pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days.WeatherItem2;
import pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects.GeoCityCoord;
import pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects.Location;
import pl.arkani.LZ_2022301_LX.service.MailService;
import pl.arkani.LZ_2022301_LX.service.WeatherService;
import pl.arkani.LZ_2022301_LX.webclient.weather.WeatherClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/*
YT: https://www.youtube.com/watch?v=DPFYyjyeuVA&t=643s
* https://home.openweathermap.org
* pass=ApiKeyForArkani
* https://api.openweathermap.org/data/2.5/weather?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metrics&lang=pl
* https://openweathermap.org/api/geocoding-api
* get maual coordinates : https://latitude.to/map/pl/poland/cities/chwaszczyno/articles/416282/nowe-tokary
* */


/*tabela html z filtrami iinymi dodatkami :https://datatables.net/*/
@Controller
@RequiredArgsConstructor
//@AllArgsConstructor
@RequestMapping(value = "/pub/weather")
public class WeatherController {

    private final WeatherService weatherService;
    private String cityName;
    private MailService mailService;

//    @Autowired
//    public WeatherController(WeatherService weatherService, String cityName, MailService mailService) {
//        this.weatherService = weatherService;
//        this.cityName = cityName;
//        this.mailService = mailService;
//    }

    @GetMapping
    public String getWeather(Model model) {

//        try {
//            mailService.sendMail("arek.kaniewski@gmail.com",
//                    //"tech.email.phone@gmail.com",
//                    "Potwierdź rejestracje","body text",false);
//
//        } catch (jakarta.mail.MessagingException e) {
//            throw new RuntimeException(e);
//        }

        //     weatherService.getObjectList();
        WeatherFinalClass01 weather = new WeatherFinalClass01();

        //paramerty startowe
        weather.setLatitude(54.405665044F);
        weather.setLongitude(18.342831962F);

//        //warszawa
//        weather.setLatitude(52.229675F);
//        weather.setLongitude(21.012230F);

        String lang="pl";

        //-------------------------------------------------------------------
        //--pobranie coordynatow po nazwie miast
        List<Location> locations = weatherService.getLocationFromCityName("Gdańsk", 5);
        weather.setLatitude((float) locations.get(0).getLat());
        weather.setLongitude((float) locations.get(0).getLon());
       // System.out.println(locations.get(0).g);
               //-------------------------------------------------------------------

     //   weather.setLang("pl");
       weather.setLang(lang);
       weather = weatherService.getWeather(weather.getLatitude(),weather.getLongitude(),weather.getLang());
       weather.setLang(lang);

        WeatherData2 weatherData2 = weatherService.getWeatherForecast(weather.getLatitude(),weather.getLongitude(),weather.getLang());
        model.addAttribute("weatherForecast",weatherData2);

  //     weather.setLang(weather.getLang());

        //String lat = "54.405665044";
        //String lon = "18.342831962";

        GeoCityCoord geoCityCoord = new GeoCityCoord();
        model.addAttribute("city",geoCityCoord);
        System.out.println("#city2:"+geoCityCoord);
        System.out.println("#cityName:"+cityName);

        model.addAttribute("weather",weather);
        model.addAttribute("locations",locations);


        //System.out.println(weather);

        return "_public/weather/weather.html";
    }


    // localhost:8080/test/weather
    //!!! dynamic method by bind by name param in html  !!!
    @PostMapping(params="addCoordinates") //w html params to znacznik "name"
    String addCulinaryRecipesStep(@ModelAttribute("weather") WeatherFinalClass01 weatherMod, @ModelAttribute("city") GeoCityCoord city, Model model
    //        ,@ModelAttribute("weatherForecast")  List<WeatherItem2> weatherForecastList
    )  {

        System.out.println("#city:"+city);
        String lang = weatherMod.getLang();

        String cityName="Gdańsk";

        if (city.getName()!=null) cityName=city.getName();


        //weatherMod = weatherService.getWeather(weatherMod.getLatitude(),weatherMod.getLongitude(),weatherMod.getLang() );
        List<Location> locations = weatherService.getLocationFromCityName(cityName,5);

        int nr=0;
        if (city.getNr()>0&&city.getNr()<=locations.size()+1)  nr=city.getNr()-1;

        weatherMod = weatherService.getWeather(locations.get(0).getLat(),locations.get(0).getLon(),weatherMod.getLang() );
        WeatherData2 weatherData2 =  weatherService.getWeatherForecast(locations.get(0).getLat(),locations.get(0).getLon(),weatherMod.getLang());

        System.out.println(weatherData2);
       // List<WeatherItem2> weatherForecastList = weatherData2.getList().stream().collect(Collectors.toList());
        List<WeatherFinalClass01> weatherForecastList = new ArrayList<>() ;

        for (int i = 0; i <  weatherData2.getList().size() ; i++) {
            WeatherFinalClass01 weatherFinalClass01 = WeatherClient.mapToWeather(weatherData2, i);
            weatherForecastList.add(weatherFinalClass01);

        }


        //weatherData2.getList().get(5).getMain().feels_like
        // weatherData2.getList().get(5).getWeather().get(0).getDescription()
        weatherForecastList.forEach(System.out::println);
        weatherMod.setLang(lang);
        model.addAttribute("weather",weatherMod);
        model.addAttribute("weatherForecastList",weatherForecastList);
        model.addAttribute("locations",locations);

        System.out.println("#weatherAddCoordinates:"+weatherMod);



        return "_public/weather/weather.html";
    }

    @PostMapping(value = "/city",params = "addCity") //
    String searchCity(@ModelAttribute("city") GeoCityCoord city, Model model) {

        System.out.println("#city:"+city);
        cityName=city.getName();

        GeoCityCoord geoCityCoord = new GeoCityCoord();
        model.addAttribute("city",geoCityCoord);

        return "_public/weather/weather.html";

    }

    //    @ModelAttribute("weather")
//    Message getMessage() {
//        return weather;
//    }

    //- ------------------- mapa --------------------------------------------------------------------------

    @GetMapping(path="/map")
    public String getMap(Model model) {
        cityName="Pruszcz Gdański";
        List<Location> locations = weatherService.getLocationFromCityName(cityName,5);

        List<Point> points = new ArrayList<>();
        for (Location l:locations) {
            points.add(new Point(l.getLat(),l.getLon(),l.getName() ));

        }
        model.addAttribute("points",points);


        return "_public/weather/weathermap";
    }
}
