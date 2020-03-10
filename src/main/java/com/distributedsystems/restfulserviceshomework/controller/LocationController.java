package com.distributedsystems.restfulserviceshomework.controller;

import com.distributedsystems.restfulserviceshomework.model.Location;
import com.distributedsystems.restfulserviceshomework.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LocationController {

    private LocationService locationService;

    @GetMapping("/{locationName}")
    public ResponseEntity<Location> getLocation(@PathVariable String locationName) {
        return ResponseEntity.ok(locationService.getFirstFoundLocation(locationName));
    }
}
