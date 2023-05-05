package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import lombok.Getter;

import java.util.List;

@Getter
public class WeatherData2 {
    private String cod;
    private int message;
    private int cnt;
    private List<WeatherInfo2> WeatherInfo;
    private City2 city;

    // getters and setters
}


