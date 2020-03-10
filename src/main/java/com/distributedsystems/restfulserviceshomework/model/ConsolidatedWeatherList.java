package com.distributedsystems.restfulserviceshomework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsolidatedWeatherList {

    private List<ConsolidatedWeather> consolidatedWeatherList;
}
