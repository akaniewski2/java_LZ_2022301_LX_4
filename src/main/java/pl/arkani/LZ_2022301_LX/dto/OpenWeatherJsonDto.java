package pl.arkani.LZ_2022301_LX.dto;

import lombok.Getter;

import java.util.List;

@Getter
//zawiera w sobie dwa inne DTO + pole name, abyu wyjsc na płaską strukture

//https://api.openweathermap.org/data/2.5/weather?lat=54.405665044&lon=18.342831962&appid=a66b540bcde954f8ef6423bf15c6ee5c&units=metric&lang=pl
public class OpenWeatherJsonDto {

    //pola odzwierciedlają trukture tablic JSON
    private OpenWeatherJsonMainDto main;
    private OpenWeatherJsonWindDto wind;
    private List<OpenWeatherJsonWeatherDto> weather;
    private OpenWeatherJsonCoordDto coord;
    private OpenWeatherJsonSysDto sys;
   // private OpenWeatherJsonWeatherDto weather;

    private String name;
    private long dt;
}

/*

{
    "coord": {
        "lon": 18.3428,
        "lat": 54.4057
    },
    "weather": [
        {
            "id": 800,
            "main": "Clear",
            "description": "bezchmurnie",
            "icon": "01n"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 4.55,
        "feels_like": 3.38,
        "temp_min": 2.75,
        "temp_max": 6.09,
        "pressure": 1022,
        "humidity": 83
    },
    "visibility": 10000,
    "wind": {
        "speed": 1.54,
        "deg": 120
    },
    "clouds": {
        "all": 0
    },
    "dt": 1683057430,
    "sys": {
        "type": 2,
        "id": 2030689,
        "country": "PL",
        "sunrise": 1682996847,
        "sunset": 1683051582
    },
    "timezone": 7200,
    "id": 3103882,
    "name": "Banino",
    "cod": 200
}

 */