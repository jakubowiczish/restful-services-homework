package com.distributedsystems.restfulserviceshomework.service.weather;

import com.distributedsystems.restfulserviceshomework.model.weather.external.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.weather.internal.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.distributedsystems.restfulserviceshomework.util.Constants.META_WEATHER_BASE_URL;
import static com.distributedsystems.restfulserviceshomework.util.Utils.*;
import static com.distributedsystems.restfulserviceshomework.util.WeatherMathUtil.*;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherService {

    public WeatherResponse getWeatherForSingleDay(final LocationInternal locationInternal,
                                                  final LocalDate date) {
        final List<ConsolidatedWeather> weatherList = getConsolidatedWeatherForSingleDay(locationInternal, date);

        return WeatherResponse.builder()
                .information(createInformationStringForSingleDayWeather(date))
                .city(locationInternal.getTitle())
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

    public WeatherResponse getWeatherForDateRange(final LocationInternal locationInternal,
                                                  final LocalDate startDate,
                                                  final LocalDate endDate) {
        List<List<ConsolidatedWeather>> weatherListOfLists = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final List<ConsolidatedWeather> weatherList = getConsolidatedWeatherForSingleDay(locationInternal, date);
            weatherListOfLists.add(weatherList);
        }

        return WeatherResponse.builder()
                .information(createInformationStringForDateRangeWeather(startDate, endDate))
                .city(locationInternal.getTitle())
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

    private List<ConsolidatedWeather> getConsolidatedWeatherForSingleDay(final LocationInternal locationInternal,
                                                                         final LocalDate date) {
        return getConsolidatedWeatherWithForecastForSingleDay(locationInternal, date)
                .stream()
                .filter(e -> isTheSameDate(e.getCreated(), date))
                .collect(toList());
    }

    private List<ConsolidatedWeather> getConsolidatedWeatherWithForecastForSingleDay(final LocationInternal locationInternal,
                                                                                     final LocalDate date) {
        return Arrays.stream(requireNonNull(new RestTemplate()
                .getForEntity(
                        getWeatherUriForDate(locationInternal, date),
                        ConsolidatedWeather[].class)
                .getBody()))
                .collect(toList());
    }

    private String getWeatherUriForDate(final LocationInternal locationInternal,
                                        final LocalDate date) {
        return UriComponentsBuilder
                .fromHttpUrl(META_WEATHER_BASE_URL + "/{id}/{year}/{month}/{day}")
                .buildAndExpand(
                        locationInternal.getWhereOnEarthId(),
                        date.getYear(),
                        date.getMonthValue(),
                        date.getDayOfMonth())
                .toUriString();
    }
}
