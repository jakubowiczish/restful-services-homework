package com.distributedsystems.restfulserviceshomework.response.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

    private String information;
    private String city;
    private TemperatureResponse temperature;
    private AirPressureResponse airPressure;
    private WindResponse wind;
    private HumidityResponse humidity;
    private VisibilityResponse visibility;
}
