package com.distributedsystems.restfulserviceshomework.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private String title;
    private String location_type;
    private String latt_long;
    private int woeid;
}
