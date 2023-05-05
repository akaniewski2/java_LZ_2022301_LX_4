package pl.arkani.LZ_2022301_LX.model;

import lombok.*;

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

    private long dt;

    public String getFormattedDt () {
        String formattedDt = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date (this.dt*1000));
        //Epoch in seconds, remove '*1000' for milliseconds.
        return formattedDt;
    }



}
