package com.distributedsystems.restfulserviceshomework.service;

import com.distributedsystems.restfulserviceshomework.model.Location;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

import static com.distributedsystems.restfulserviceshomework.util.Constants.SEARCH_QUERY;

@Service
public class LocationService {

    @SneakyThrows
    public Location getFirstFoundLocation(String location) {
        return Arrays.stream(getLocationList(location))
                .findFirst()
                .orElseThrow(Exception::new); // TODO: better exception handling
    }

    private Location[] getLocationList(String location) {
        return Objects.requireNonNull(new RestTemplate()
                .getForEntity(SEARCH_QUERY + location, Location[].class)
                .getBody());
    }
}
