package pl.arkani.LZ_2022301_LX.model.WeatherJsonObjectFor4Days;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rain2{
    @JsonProperty("3h")
    public double _3h;
}