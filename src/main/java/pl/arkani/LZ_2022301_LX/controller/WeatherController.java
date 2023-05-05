package pl.arkani.LZ_2022301_LX.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.CulinaryIngredients;
import pl.arkani.LZ_2022301_LX.model.CulinaryRecipes;
import pl.arkani.LZ_2022301_LX.model.Message;
import pl.arkani.LZ_2022301_LX.model.WeatherFinalClass01;
import pl.arkani.LZ_2022301_LX.service.WeatherService;


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
@RequestMapping(value = "/pub/weather")
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping
    public String getWeather(Model model) {
        //     weatherService.getObjectList();
        WeatherFinalClass01 weather = new WeatherFinalClass01();

        //paramerty startowe
        weather.setLatitude(54.405665044F);
        weather.setLongitude(18.342831962F);

//        //warszawa
//        weather.setLatitude(52.229675F);
//        weather.setLongitude(21.012230F);

        String lang="pl";


     //   weather.setLang("pl");
       weather.setLang(lang);
       weather = weatherService.getWeather(weather.getLatitude(),weather.getLongitude(),weather.getLang());
       weather.setLang(lang);
  //     weather.setLang(weather.getLang());

        //String lat = "54.405665044";
        //String lon = "18.342831962";

        model.addAttribute("weather",weather);

        //System.out.println(weather);

        return "_public/weather/weather.html";
    }


    // localhost:8080/test/weather
    //!!! dynamic method by bind by name param in html  !!!
    @PostMapping(params="addCoordinates") //w html params to znacznik "name"
    String addCulinaryRecipesStep(@ModelAttribute("weather") WeatherFinalClass01 weatherMod,Model model)  {

        String lang = weatherMod.getLang();

        weatherMod = weatherService.getWeather(weatherMod.getLatitude(),weatherMod.getLongitude(),weatherMod.getLang() );
        weatherMod.setLang(lang);
        model.addAttribute("weather",weatherMod);

        System.out.println("#weatherAddCoordinates:"+weatherMod);



        return "_public/weather/weather.html";
    }

//    @ModelAttribute("weather")
//    Message getMessage() {
//        return weather;
//    }
}
