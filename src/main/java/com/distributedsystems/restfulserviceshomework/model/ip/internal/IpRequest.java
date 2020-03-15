package com.distributedsystems.restfulserviceshomework.model.ip.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpRequest {

    private String ipAddresses;
    private String city;
    private String countryCode;
    private String continentCode;
    private String organizationName;
}
