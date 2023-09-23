package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather2 {
    private int id;
    private String main;
    private String description;
    private String icon;

    // getters and setters
}
