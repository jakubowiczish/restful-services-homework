package com.distributedsystems.restfulserviceshomework.model;

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
    private LocalDate applicable_date;
    private String weather_state_name;
    private String weather_state_abbr;
    private double wind_speed;
    private double wind_direction;
    private String wind_direction_compass;
    private LocalDateTime created;
    private double min_temp;
    private double max_temp;
    private double the_temp;
    private double air_pressure;
    private double humidity;
    private double visibility;
    private int predictability;
}
