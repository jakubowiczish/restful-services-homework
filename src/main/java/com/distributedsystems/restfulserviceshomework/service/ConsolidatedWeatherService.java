package com.distributedsystems.restfulserviceshomework.service;


import com.distributedsystems.restfulserviceshomework.model.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.Location;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.distributedsystems.restfulserviceshomework.util.Constants.META_WEATHER_BASE_URL;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ConsolidatedWeatherService {

    public List<ConsolidatedWeather> getConsolidatedWeatherForSingleDay(Location location, LocalDate date) {
        String url = META_WEATHER_BASE_URL
                + "/" + location.getWoeid()
                + "/" + date.getYear()
                + "/" + date.getMonthValue()
                + "/" + date.getDayOfMonth();

        return Arrays.stream(Objects.requireNonNull(new RestTemplate()
                .getForEntity(url, ConsolidatedWeather[].class)
                .getBody()))
                .filter(e -> isCreatedDateToday(e.getCreated(), date))
                .collect(toList());
    }

    private boolean isCreatedDateToday(LocalDateTime localDateTime, LocalDate localDate) {
        return localDateTime.toLocalDate().isEqual(localDate);
    }
}
