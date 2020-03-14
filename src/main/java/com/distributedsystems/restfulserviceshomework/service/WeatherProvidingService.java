package com.distributedsystems.restfulserviceshomework.service;

import com.distributedsystems.restfulserviceshomework.model.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.Location;
import com.distributedsystems.restfulserviceshomework.response.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.ToDoubleFunction;

import static com.distributedsystems.restfulserviceshomework.util.Utils.createInformationStringForDateRangeWeather;
import static com.distributedsystems.restfulserviceshomework.util.Utils.createInformationStringForSingleDayWeather;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherProvidingService {

    private WeatherGainingService weatherGainingService;

    public WeatherResponse getWeatherForSingleDay(final Location location, final LocalDate date) {
        final List<ConsolidatedWeather> weatherList
                = weatherGainingService.getConsolidatedWeatherForSingleDay(location, date);

        return WeatherResponse.builder()
                .information(createInformationStringForSingleDayWeather(date))
                .city(location.getTitle())
                .wind(WindResponse.builder()
                        .average(getAverage(weatherList, ConsolidatedWeather::getWindSpeed))
                        .minimum(getAverage(weatherList, ConsolidatedWeather::getWindSpeed))
                        .maximum(getAverage(weatherList, ConsolidatedWeather::getWindSpeed))
                        .build())
                .temperature(TemperatureResponse.builder()
                        .average(getAverage(weatherList, ConsolidatedWeather::getTheTemp))
                        .minimum(getMinimum(weatherList, ConsolidatedWeather::getMinTemp))
                        .maximum(getMaximum(weatherList, ConsolidatedWeather::getMaxTemp))
                        .build())
                .airPressure(AirPressureResponse.builder()
                        .average(getAverage(weatherList, ConsolidatedWeather::getAirPressure))
                        .minimum(getMinimum(weatherList, ConsolidatedWeather::getAirPressure))
                        .maximum(getMaximum(weatherList, ConsolidatedWeather::getAirPressure))
                        .build())
                .humidity(HumidityResponse.builder()
                        .average(getAverage(weatherList, ConsolidatedWeather::getHumidity))
                        .minimum(getMinimum(weatherList, ConsolidatedWeather::getHumidity))
                        .maximum(getMaximum(weatherList, ConsolidatedWeather::getHumidity))
                        .build())
                .visibility(VisibilityResponse.builder()
                        .average(getAverage(weatherList, ConsolidatedWeather::getVisibility))
                        .minimum(getMinimum(weatherList, ConsolidatedWeather::getVisibility))
                        .maximum(getMaximum(weatherList, ConsolidatedWeather::getVisibility))
                        .build())
                .build();
    }

    public WeatherResponse getWeatherForDateRange(final Location location, final LocalDate startDate, final LocalDate endDate) {
        return WeatherResponse.builder()
                .information(createInformationStringForDateRangeWeather(startDate, endDate))
                .city(location.getTitle())
                .wind(WindResponse.builder()
                        .average(getAverageForDateRange(location, startDate, endDate, ConsolidatedWeather::getWindSpeed))
                        .minimum(getMinimumForDateRange(location, startDate, endDate, ConsolidatedWeather::getWindSpeed))
                        .maximum(getMaximumForDateRange(location, startDate, endDate, ConsolidatedWeather::getWindSpeed))
                        .build())
                .temperature(TemperatureResponse.builder()
                        .average(getAverageForDateRange(location, startDate, endDate, ConsolidatedWeather::getTheTemp))
                        .minimum(getMinimumForDateRange(location, startDate, endDate, ConsolidatedWeather::getMinTemp))
                        .maximum(getMaximumForDateRange(location, startDate, endDate, ConsolidatedWeather::getMaxTemp))
                        .build())
                .airPressure(AirPressureResponse.builder()
                        .average(getAverageForDateRange(location, startDate, endDate, ConsolidatedWeather::getAirPressure))
                        .minimum(getMinimumForDateRange(location, startDate, endDate, ConsolidatedWeather::getAirPressure))
                        .maximum(getMaximumForDateRange(location, startDate, endDate, ConsolidatedWeather::getAirPressure))
                        .build())
                .humidity(HumidityResponse.builder()
                        .average(getAverageForDateRange(location, startDate, endDate, ConsolidatedWeather::getHumidity))
                        .minimum(getMinimumForDateRange(location, startDate, endDate, ConsolidatedWeather::getHumidity))
                        .maximum(getMaximumForDateRange(location, startDate, endDate, ConsolidatedWeather::getHumidity))
                        .build())
                .visibility(VisibilityResponse.builder()
                        .average(getAverageForDateRange(location, startDate, endDate, ConsolidatedWeather::getVisibility))
                        .minimum(getMinimumForDateRange(location, startDate, endDate, ConsolidatedWeather::getVisibility))
                        .maximum(getMaximumForDateRange(location, startDate, endDate, ConsolidatedWeather::getVisibility))
                        .build())
                .build();
    }

    private double getAverage(final List<ConsolidatedWeather> weatherList,
                              final ToDoubleFunction<ConsolidatedWeather> function) {
        if (weatherList == null || weatherList.isEmpty()) return 0;

        double sum = weatherList.stream()
                .mapToDouble(function)
                .sum();

        return sum / weatherList.size();
    }

    private double getMinimum(final List<ConsolidatedWeather> weatherList,
                              final ToDoubleFunction<ConsolidatedWeather> function) {
        return weatherList.stream()
                .mapToDouble(function)
                .min()
                .orElse(Double.MAX_VALUE);
    }

    private double getMaximum(final List<ConsolidatedWeather> weatherList,
                              final ToDoubleFunction<ConsolidatedWeather> function) {
        return weatherList.stream()
                .mapToDouble(function)
                .max()
                .orElse(Double.MIN_VALUE);
    }

    private double getAverageForDateRange(final Location location,
                                          final LocalDate startDate,
                                          final LocalDate endDate,
                                          final ToDoubleFunction<ConsolidatedWeather> function) {
        double sum = 0;
        int numberOfDays = 0;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList
                    = weatherGainingService.getConsolidatedWeatherForSingleDay(location, date);

            sum += getAverage(weatherList, function);
            numberOfDays++;
        }

        return numberOfDays == 0 ? 0 : sum / numberOfDays;
    }

    private double getMinimumForDateRange(final Location location,
                                          final LocalDate startDate,
                                          final LocalDate endDate,
                                          final ToDoubleFunction<ConsolidatedWeather> function) {
        double min = Double.MAX_VALUE;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList
                    = weatherGainingService.getConsolidatedWeatherForSingleDay(location, date);

            min = Math.min(min, getMinimum(weatherList, function));
        }

        return min;
    }

    private double getMaximumForDateRange(final Location location,
                                          final LocalDate startDate,
                                          final LocalDate endDate,
                                          final ToDoubleFunction<ConsolidatedWeather> function) {
        double max = Double.MIN_VALUE;
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList
                    = weatherGainingService.getConsolidatedWeatherForSingleDay(location, date);

            max = Math.max(max, getMaximum(weatherList, function));
        }

        return max;
    }
}
