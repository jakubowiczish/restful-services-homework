package com.distributedsystems.restfulserviceshomework.model.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidatedWeather {

    private BigInteger id;

    @JsonProperty("applicable_date")
    private LocalDate applicableDate;

    @JsonProperty("weather_state_name")
    private String weatherStateName;

    @JsonProperty("weather_state_abbr")
    private String weatherStateAbbr;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("wind_direction")
    private double windDirection;

    @JsonProperty("wind_direction_compass")
    private String windDirectionCompass;

    private LocalDateTime created;

    @JsonProperty("min_temp")
    private double minTemp;

    @JsonProperty("max_temp")
    private double maxTemp;

    @JsonProperty("the_temp")
    private double theTemp;

    @JsonProperty("air_pressure")
    private double airPressure;

    private double humidity;

    private double visibility;

    private int predictability;
}
