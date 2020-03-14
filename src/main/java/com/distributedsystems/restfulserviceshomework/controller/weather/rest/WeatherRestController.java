package com.distributedsystems.restfulserviceshomework.controller.weather.rest;

import com.distributedsystems.restfulserviceshomework.model.weather.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.weather.Location;
import com.distributedsystems.restfulserviceshomework.response.weather.WeatherResponse;
import com.distributedsystems.restfulserviceshomework.service.weather.LocationService;
import com.distributedsystems.restfulserviceshomework.service.weather.MetaWeatherInformationGatheringService;
import com.distributedsystems.restfulserviceshomework.service.weather.WeatherProvidingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/statistics/weather")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherRestController {

    private LocationService locationService;
    private WeatherProvidingService weatherProvidingService;
    private MetaWeatherInformationGatheringService metaWeatherInformationGatheringService;

    @GetMapping("/singleDay/{locationName}")
    public ResponseEntity<WeatherResponse> getTemperatureForSingleDay(@PathVariable String locationName,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        final Location location = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(weatherProvidingService.getWeatherForSingleDay(location, date));
    }

    @GetMapping("/dateRange/{locationName}")
    public ResponseEntity<WeatherResponse> getTemperatureForDateRange(@PathVariable String locationName,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        final Location location = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(weatherProvidingService.getWeatherForDateRange(location, startDate, endDate));
    }

    @GetMapping("/consolidatedWeather/{locationName}")
    public ResponseEntity<List<ConsolidatedWeather>> getWeatherForSingleDay(@PathVariable String locationName,
                                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Location location = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(metaWeatherInformationGatheringService.getConsolidatedWeatherForSingleDay(location, date));
    }
}
