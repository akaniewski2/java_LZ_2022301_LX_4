package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjects;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class WeatherData {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    
    // getters and setters
}