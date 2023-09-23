package pl.arkani.LZ_2022301_LX.model;

import lombok.*;
import pl.arkani.LZ_2022301_LX.utils.Functions;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//finalna płaska struktura do wyjscia w conroller
public class WeatherFinalClass01 {

    private float temperature;
    private float feelsLike;
    private float temperatureMin;
    private float temperatureMax;
    private int pressure;
    private int humidity;

    private String main;
    private String description;

    private float windSpeed;

    private String nearestCity;

    private float latitude;
    private float longitude;

    private String country;
    private String lang = country;

    private long sunrise;
    private long sunset;
    private long dt;

    public String getFormattedDt () {
        String formattedDt = Functions.formatEpochToString(this.dt,"yyyy-MM-dd HH:mm:ss");
        //Epoch in seconds, remove '*1000' for milliseconds.
        return formattedDt;
    }
    public String getFormattedSunriseDt () {
        String formattedDt = Functions.formatEpochToString(this.sunrise,"yyyy-MM-dd HH:mm:ss");
        //Epoch in seconds, remove '*1000' for milliseconds.
        return formattedDt;
    }


    public String getFormattedSunsetDt () {
        String formattedDt = Functions.formatEpochToString(this.sunset,"yyyy-MM-dd HH:mm:ss");
        //Epoch in seconds, remove '*1000' for milliseconds.
        return formattedDt;
    }


}
