package com.distributedsystems.restfulserviceshomework.model.ip.internal;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpInternalResponse {

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
    private String organizationName;
    private String as;
    private String asname;
    private String reverse;
    private boolean mobile;
    private boolean proxy;
    private boolean hosting;
    private String query;
}
