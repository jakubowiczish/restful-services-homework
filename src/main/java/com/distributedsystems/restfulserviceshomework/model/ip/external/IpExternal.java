package com.distributedsystems.restfulserviceshomework.model.ip.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpExternal {

    private String status;
    private String message;
    private String continent;
    private String continentCode;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String district;
    private String zip;
    private double lat;
    private double lon;
    private String timezone;
    private String currency;
    private String isp;

    @JsonProperty("org")
    private String organizationName;
    private String as;
    private String asname;
    private String reverse;
    private boolean mobile;
    private boolean proxy;
    private boolean hosting;
    private String query;
}
