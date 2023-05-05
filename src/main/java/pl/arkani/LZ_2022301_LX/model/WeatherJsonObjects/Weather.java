package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjects;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
    
    // getters and setters
}
