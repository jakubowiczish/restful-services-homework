package com.distributedsystems.restfulserviceshomework.service;

import com.distributedsystems.restfulserviceshomework.model.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.Location;
import com.distributedsystems.restfulserviceshomework.response.TemperatureResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TemperatureService {

    private WeatherService weatherService;

    public TemperatureResponse getTemperatureForSingleDay(final Location location, final LocalDate date) {
        final List<ConsolidatedWeather> weatherList
                = weatherService.getConsolidatedWeatherForSingleDay(location, date);

        return TemperatureResponse.builder()
                .averageTemperature(getAverageTemperatureForSingleDay(weatherList, location, date))
                .minimumTemperature(getMinimumTemperatureForSingleDay(weatherList, location, date))
                .maximumTemperature(getMaximumTemperatureForSingleDay(weatherList, location, date))
                .build();
    }

    public TemperatureResponse getTemperatureForDateRange(final Location location, final LocalDate startDate, final LocalDate endDate) {
        return TemperatureResponse.builder()
                .averageTemperature(getAverageTemperatureForDateRange(location, startDate, endDate))
                .minimumTemperature(getMinimumTemperatureForDateRange(location, startDate, endDate))
                .maximumTemperature(getMaximumTemperatureForDateRange(location, startDate, endDate))
                .build();
    }

    private double getAverageTemperatureForSingleDay(final List<ConsolidatedWeather> weatherList, final Location location, final LocalDate date) {
        if (weatherList == null || weatherList.isEmpty()) return 0;

        double sum = weatherList.stream()
                .mapToDouble(ConsolidatedWeather::getTheTemp)
                .sum();

        return sum / weatherList.size();
    }

    private double getMinimumTemperatureForSingleDay(final List<ConsolidatedWeather> weatherList, final Location location, final LocalDate date) {
        return weatherList.stream()
                .mapToDouble(ConsolidatedWeather::getMinTemp)
                .min()
                .orElse(Double.MAX_VALUE);
    }

    private double getMaximumTemperatureForSingleDay(final List<ConsolidatedWeather> weatherList, final Location location, final LocalDate date) {
        return weatherList.stream()
                .mapToDouble(ConsolidatedWeather::getMaxTemp)
                .max()
                .orElse(Double.MIN_VALUE);
    }

    private double getAverageTemperatureForDateRange(final Location location, final LocalDate startDate, final LocalDate endDate) {
        double sum = 0;
        int numberOfDays = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList
                    = weatherService.getConsolidatedWeatherForSingleDay(location, date);

            sum += getAverageTemperatureForSingleDay(weatherList, location, date);
            numberOfDays++;
        }

        return numberOfDays == 0 ? 0 : sum / numberOfDays;
    }

    private double getMinimumTemperatureForDateRange(final Location location, final LocalDate startDate, final LocalDate endDate) {
        double min = Double.MAX_VALUE;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList
                    = weatherService.getConsolidatedWeatherForSingleDay(location, date);

            min = Math.min(min, getMinimumTemperatureForSingleDay(weatherList, location, date));
        }

        return min;
    }

    private double getMaximumTemperatureForDateRange(final Location location, final LocalDate startDate, final LocalDate endDate) {
        double max = Double.MIN_VALUE;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList
                    = weatherService.getConsolidatedWeatherForSingleDay(location, date);

            max = Math.max(max, getMaximumTemperatureForSingleDay(weatherList, location, date));
        }

        return max;
    }
}
