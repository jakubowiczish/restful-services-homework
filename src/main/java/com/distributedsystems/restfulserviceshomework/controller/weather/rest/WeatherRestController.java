package com.distributedsystems.restfulserviceshomework.controller.weather.rest;

import com.distributedsystems.restfulserviceshomework.model.weather.internal.LocationInternal;
import com.distributedsystems.restfulserviceshomework.model.weather.internal.WeatherResponse;
import com.distributedsystems.restfulserviceshomework.service.weather.LocationService;
import com.distributedsystems.restfulserviceshomework.service.weather.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistics/weather")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherRestController {

    private LocationService locationService;
    private WeatherService weatherService;

    @GetMapping("/singleDay/{locationName}")
    public ResponseEntity<WeatherResponse> getTemperatureForSingleDay(@PathVariable String locationName,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        final LocationInternal locationInternal = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(weatherService.getWeatherForSingleDay(locationInternal, date));
    }

    @GetMapping("/dateRange/{locationName}")
    public ResponseEntity<WeatherResponse> getTemperatureForDateRange(@PathVariable String locationName,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        final LocationInternal locationInternal = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(weatherService.getWeatherForDateRange(locationInternal, startDate, endDate));
    }
}
