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
public class WeatherData2 {
    private String cod;
    private int message;
    private int cnt;
    private List<WeatherItem2> list;
    private City2 city;

    // getters and setters
}


