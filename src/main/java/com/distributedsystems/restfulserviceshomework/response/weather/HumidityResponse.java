package com.distributedsystems.restfulserviceshomework.response.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HumidityResponse {

    private final String unit = "%";
    private double minimum;
    private double average;
    private double maximum;
}
