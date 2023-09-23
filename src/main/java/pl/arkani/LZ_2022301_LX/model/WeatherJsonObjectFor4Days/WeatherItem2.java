package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherItem2 {
    private long dt;
    private Main2 main;
    private List<Weather2> weather;
    private Clouds2 clouds;
    private Wind2 wind;
    private int visibility;
    private int pop;
    private Sys2 sys;
    private String dt_txt;

    // getters and setters
}