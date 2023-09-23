package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainList2 {
    public int dt;
    public Main2 main;
    public ArrayList<Weather2> weather;
    public Clouds2 clouds;
    public Wind2 wind;
    public int visibility;
    public double pop;
    public Sys2 sys;
    public String dt_txt;
    public Rain2 rain;
}