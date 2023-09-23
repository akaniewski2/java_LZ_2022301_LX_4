package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Root2 {
    public String cod;
    public int message;
    public int cnt;
    public ArrayList<List> list;
    public City2 city;
}