package com.distributedsystems.restfulserviceshomework.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureResponse {

    private double averageTemperature;
    private double minimumTemperature;
    private double maximumTemperature;
}
