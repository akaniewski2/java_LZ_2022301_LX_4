package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjects;

import lombok.Getter;

@Getter
public class Sys {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;
    
    // getters and setters
}