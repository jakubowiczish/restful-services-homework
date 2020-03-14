package com.distributedsystems.restfulserviceshomework.service;

import com.distributedsystems.restfulserviceshomework.exception.LocationNotFoundException;
import com.distributedsystems.restfulserviceshomework.model.Location;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.distributedsystems.restfulserviceshomework.util.Constants.SEARCH_QUERY;
import static java.util.Objects.requireNonNull;

@Service
public class LocationService {

    @SneakyThrows
    public Location getFirstFoundLocation(String location) {
        return getLocationList(location).stream()
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("There is no location associated with: " + location));
    }

    private List<Location> getLocationList(String location) {
        return List.of(requireNonNull(
                new RestTemplate()
                        .getForEntity(SEARCH_QUERY + location, Location[].class)
                        .getBody()));
    }
}
