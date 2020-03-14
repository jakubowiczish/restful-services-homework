package com.distributedsystems.restfulserviceshomework.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureResponse {

    private String city;
    private double averageTemperature;
    private double minimumTemperature;
    private double maximumTemperature;
}
