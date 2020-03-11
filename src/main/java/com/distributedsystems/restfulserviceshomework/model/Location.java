package com.distributedsystems.restfulserviceshomework.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String title;

    @JsonProperty("location_type")
    private String locationType;

    @JsonProperty("latt_long")
    private String latitudeLongitude;

    @JsonProperty("woeid")
    private int whereOnEarthId;
}
