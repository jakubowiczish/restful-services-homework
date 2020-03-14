package com.distributedsystems.restfulserviceshomework.request.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureSingleDayRequest {

    private String locationName;
    private LocalDate date;
}
