package com.distributedsystems.restfulserviceshomework.response;

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
    private double average;
    private double minimum;
    private double maximum;
}
