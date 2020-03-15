package com.distributedsystems.restfulserviceshomework.model.weather.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationInternal {

    private String title;
    private int whereOnEarthId;
}
