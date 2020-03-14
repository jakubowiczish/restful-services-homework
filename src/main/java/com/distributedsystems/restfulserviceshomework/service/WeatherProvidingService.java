package com.distributedsystems.restfulserviceshomework.service;

import com.distributedsystems.restfulserviceshomework.model.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.Location;
import com.distributedsystems.restfulserviceshomework.response.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

import static com.distributedsystems.restfulserviceshomework.util.Utils.createInformationStringForDateRangeWeather;
import static com.distributedsystems.restfulserviceshomework.util.Utils.createInformationStringForSingleDayWeather;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherProvidingService {

    private MetaWeatherInformationGatheringService metaWeatherInformationGatheringService;

    public WeatherResponse getWeatherForSingleDay(final Location location,
                                                  final LocalDate date) {
        final List<ConsolidatedWeather> weatherList
                = metaWeatherInformationGatheringService.getConsolidatedWeatherForSingleDay(location, date);

        return WeatherResponse.builder()
                .information(createInformationStringForSingleDayWeather(date))
                .city(location.getTitle())
                .wind(WindResponse.builder()
                        .average(getAverage(weatherList, ConsolidatedWeather::getWindSpeed))
                        .minimum(getMinimum(weatherList, ConsolidatedWeather::getWindSpeed))
                        .maximum(getMaximum(weatherList, ConsolidatedWeather::getWindSpeed))
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

    public WeatherResponse getWeatherForDateRange(final Location location,
                                                  final LocalDate startDate,
                                                  final LocalDate endDate) {
        List<List<ConsolidatedWeather>> weatherListOfLists = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList
                    = metaWeatherInformationGatheringService.getConsolidatedWeatherForSingleDay(location, date);
            weatherListOfLists.add(weatherList);
        }

        return WeatherResponse.builder()
                .information(createInformationStringForDateRangeWeather(startDate, endDate))
                .city(location.getTitle())
                .wind(WindResponse.builder()
                        .average(getAverageForDateRange(weatherListOfLists, ConsolidatedWeather::getWindSpeed))
                        .minimum(getMinimumForDateRange(weatherListOfLists, ConsolidatedWeather::getWindSpeed))
                        .maximum(getMaximumForDateRange(weatherListOfLists, ConsolidatedWeather::getWindSpeed))
                        .build())
                .temperature(TemperatureResponse.builder()
                        .average(getAverageForDateRange(weatherListOfLists, ConsolidatedWeather::getTheTemp))
                        .minimum(getMinimumForDateRange(weatherListOfLists, ConsolidatedWeather::getMinTemp))
                        .maximum(getMaximumForDateRange(weatherListOfLists, ConsolidatedWeather::getMaxTemp))
                        .build())
                .airPressure(AirPressureResponse.builder()
                        .average(getAverageForDateRange(weatherListOfLists, ConsolidatedWeather::getAirPressure))
                        .minimum(getMinimumForDateRange(weatherListOfLists, ConsolidatedWeather::getAirPressure))
                        .maximum(getMaximumForDateRange(weatherListOfLists, ConsolidatedWeather::getAirPressure))
                        .build())
                .humidity(HumidityResponse.builder()
                        .average(getAverageForDateRange(weatherListOfLists, ConsolidatedWeather::getHumidity))
                        .minimum(getMinimumForDateRange(weatherListOfLists, ConsolidatedWeather::getHumidity))
                        .maximum(getMaximumForDateRange(weatherListOfLists, ConsolidatedWeather::getHumidity))
                        .build())
                .visibility(VisibilityResponse.builder()
                        .average(getAverageForDateRange(weatherListOfLists, ConsolidatedWeather::getVisibility))
                        .minimum(getMinimumForDateRange(weatherListOfLists, ConsolidatedWeather::getVisibility))
                        .maximum(getMaximumForDateRange(weatherListOfLists, ConsolidatedWeather::getVisibility))
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

    private double getAverageForDateRange(final List<List<ConsolidatedWeather>> weatherListOfLists,
                                          final ToDoubleFunction<ConsolidatedWeather> function) {
        double sum = 0;
        int numberOfDays = 0;
        for (List<ConsolidatedWeather> weatherList : weatherListOfLists) {
            sum += getAverage(weatherList, function);
            ++numberOfDays;
        }

        return numberOfDays == 0 ? 0 : sum / numberOfDays;
    }

    private double getMinimumForDateRange(final List<List<ConsolidatedWeather>> weatherListOfLists,
                                          final ToDoubleFunction<ConsolidatedWeather> function) {
        double min = Double.MAX_VALUE;
        for (List<ConsolidatedWeather> weatherList : weatherListOfLists) {
            min = Math.min(min, getMinimum(weatherList, function));
            System.out.println(min);
        }

        return min;
    }

    private double getMaximumForDateRange(final List<List<ConsolidatedWeather>> weatherListOfLists,
                                          final ToDoubleFunction<ConsolidatedWeather> function) {
        double max = Double.MIN_VALUE;
        for (List<ConsolidatedWeather> weatherList : weatherListOfLists) {
            max = Math.max(max, getMaximum(weatherList, function));
        }

        return max;
    }
}
