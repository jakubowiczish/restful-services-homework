package com.distributedsystems.restfulserviceshomework.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WindResponse {

    private final String unit = "miles per hour";
    private double average;
    private double minimum;
    private double maximum;
}
