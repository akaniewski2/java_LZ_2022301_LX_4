package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjects;

import lombok.Getter;

@Getter
public class Main {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
    
    // getters and setters
}
