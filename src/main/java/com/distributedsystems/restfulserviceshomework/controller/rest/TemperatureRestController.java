package com.distributedsystems.restfulserviceshomework.controller.rest;

import com.distributedsystems.restfulserviceshomework.model.Location;
import com.distributedsystems.restfulserviceshomework.response.TemperatureResponse;
import com.distributedsystems.restfulserviceshomework.service.LocationService;
import com.distributedsystems.restfulserviceshomework.service.TemperatureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/statistics/temperature")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TemperatureRestController {

    private LocationService locationService;
    private TemperatureService temperatureService;

    @GetMapping("/singleDay/{locationName}")
    public ResponseEntity<TemperatureResponse> getTemperatureForSingleDay(@PathVariable String locationName,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        final Location location = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(temperatureService.getTemperatureForSingleDay(location, date));
    }

    @GetMapping("/dateRange/{locationName}")
    public ResponseEntity<TemperatureResponse> getTemperatureForDateRange(@PathVariable String locationName,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        final Location location = locationService.getFirstFoundLocation(locationName);
        return ResponseEntity.ok(temperatureService.getTemperatureForDateRange(location, startDate, endDate));
    }
}
