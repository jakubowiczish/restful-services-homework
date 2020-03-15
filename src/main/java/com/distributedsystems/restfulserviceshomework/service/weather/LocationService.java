package com.distributedsystems.restfulserviceshomework.service.weather;

import com.distributedsystems.restfulserviceshomework.exception.LocationNotFoundException;
import com.distributedsystems.restfulserviceshomework.model.weather.external.LocationExternal;
import com.distributedsystems.restfulserviceshomework.model.weather.internal.LocationInternal;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.distributedsystems.restfulserviceshomework.util.Constants.META_WEATHER_SEARCH_URL;
import static java.util.Objects.requireNonNull;

@Service
public class LocationService {

    @SneakyThrows
    public LocationInternal getFirstFoundLocation(String location) {
        return convert(getLocationList(location).stream()
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("There is no location associated with: " + location)));
    }

    private List<LocationExternal> getLocationList(String location) {
        return List.of(requireNonNull(
                new RestTemplate()
                        .getForEntity(META_WEATHER_SEARCH_URL + location, LocationExternal[].class)
                        .getBody()));
    }

    private LocationInternal convert(LocationExternal locationExternal) {
        return LocationInternal.builder()
                .whereOnEarthId(locationExternal.getWhereOnEarthId())
                .title(locationExternal.getTitle())
                .build();
    }
}
