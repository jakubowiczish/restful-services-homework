package com.distributedsystems.restfulserviceshomework.model.weather.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirPressureResponse {

    private final String unit = "hPa";
    private double minimum;
    private double average;
    private double maximum;
}
