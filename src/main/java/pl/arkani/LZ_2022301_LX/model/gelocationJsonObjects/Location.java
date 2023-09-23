package pl.arkani.LZ_2022301_LX.model.gelocationJsonObjects;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@Data
public class Location {
    private String name;
    private Map<String, String> local_names;
    private float lat;
    private float lon;
    private String country;
    private String state;
//    private GeoLocationList geoLocationList;

    // getters and setters
}