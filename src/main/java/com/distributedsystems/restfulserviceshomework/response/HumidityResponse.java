package com.distributedsystems.restfulserviceshomework.response;

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
    private double average;
    private double minimum;
    private double maximum;
}
