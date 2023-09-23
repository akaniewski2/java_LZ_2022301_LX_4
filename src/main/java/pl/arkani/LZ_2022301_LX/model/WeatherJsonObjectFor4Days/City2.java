package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City2 {
    public int id;
    public String name;
    public Coord2 coord;
    public String country;
    public int population;
    public int timezone;
    public int sunrise;
    public int sunset;

    // getters and setters
}