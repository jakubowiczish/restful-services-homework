package com.distributedsystems.restfulserviceshomework.service;


import com.distributedsystems.restfulserviceshomework.model.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.Location;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.distributedsystems.restfulserviceshomework.util.Constants.META_WEATHER_BASE_URL;
import static com.distributedsystems.restfulserviceshomework.util.Utils.isTheSameDate;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Service
public class MetaWeatherInformationGatheringService {

    private static String getWeatherUriForDate(final Location location, final LocalDate date) {
        return UriComponentsBuilder
                .fromHttpUrl(META_WEATHER_BASE_URL + "/{id}/{year}/{month}/{day}")
                .buildAndExpand(
                        location.getWhereOnEarthId(),
                        date.getYear(),
                        date.getMonthValue(),
                        date.getDayOfMonth())
                .toUriString();
    }

    public List<ConsolidatedWeather> getConsolidatedWeatherForSingleDay(final Location location, final LocalDate date) {
        return getConsolidatedWeatherWithForecastForSingleDay(location, date)
                .stream()
                .filter(e -> isTheSameDate(e.getCreated(), date))
                .collect(toList());
    }

    private List<ConsolidatedWeather> getConsolidatedWeatherWithForecastForSingleDay(final Location location, final LocalDate date) {
        return Arrays.stream(requireNonNull(new RestTemplate()
                .getForEntity(
                        getWeatherUriForDate(location, date),
                        ConsolidatedWeather[].class)
                .getBody()))
                .collect(toList());
    }
}
