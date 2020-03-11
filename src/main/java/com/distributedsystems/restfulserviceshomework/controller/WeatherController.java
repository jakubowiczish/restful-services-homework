package com.distributedsystems.restfulserviceshomework.controller;

import com.distributedsystems.restfulserviceshomework.model.ConsolidatedWeather;
import com.distributedsystems.restfulserviceshomework.model.Location;
import com.distributedsystems.restfulserviceshomework.service.LocationService;
import com.distributedsystems.restfulserviceshomework.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherController {

    private WeatherService weatherService;
    private LocationService locationService;

    @GetMapping("/{locationName}")
    public ResponseEntity<List<ConsolidatedWeather>> getWeatherForSingleDay(@PathVariable String locationName,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        Location location = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(weatherService.getConsolidatedWeatherForSingleDay(location, date));
    }
}
