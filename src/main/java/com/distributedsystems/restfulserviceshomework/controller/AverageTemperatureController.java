package com.distributedsystems.restfulserviceshomework.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metaweather/statistics")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AverageTemperatureController {

//    @GetMapping("/averageTemperature/locationName/startDate/endDate")
//    public ResponseEntity<TemperatureResponse> getAverageTemperatureForSingleDay(@PathVariable String locationName,
//                                                                                 @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate startDate,
//                                                                                 @PathVariable @DateTimeFormat(pattern = "ddMMyyyy") LocalDate endDate) {
//
//
//
//    }
}
