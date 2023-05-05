package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import lombok.Getter;

@Getter
public class City2 {
    private int id;
    private String name;
    private Coord2 coord;
    private String country;
    private int population;
    private int timezone;
    private long sunrise;
    private long sunset;

    // getters and setters
}