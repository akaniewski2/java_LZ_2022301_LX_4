package pl.arkani.LZ_2022301_LX.dto;

import lombok.Getter;

@Getter
//pola musza miec taka nazwe jak w json
public class OpenWeatherJsonWeatherDto {

    private String main;
    private String description;
}
