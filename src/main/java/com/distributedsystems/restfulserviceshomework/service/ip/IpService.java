package com.distributedsystems.restfulserviceshomework.service.ip;

import com.distributedsystems.restfulserviceshomework.model.ip.external.IpExternal;
import com.distributedsystems.restfulserviceshomework.model.ip.internal.IpInternal;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.distributedsystems.restfulserviceshomework.util.Constants.IP_API_BASE_URL;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Service
public class IpService {

    public List<IpInternal> getIpAddressesInfo(List<String> ipAddresses) {
        return ipAddresses.stream()
                .map(this::getSingleIpInfo)
                .collect(toList());
    }

    private IpInternal getSingleIpInfo(String ipAddress) {
        return convert(requireNonNull(new RestTemplate()
                .getForEntity(getIpUri(ipAddress), IpExternal.class)
                .getBody()));
    }

    private String getIpUri(String ipAddress) {
        return UriComponentsBuilder
                .fromHttpUrl(IP_API_BASE_URL + "/{ipAddress}")
                .buildAndExpand(ipAddress)
                .toUriString();
    }

    private IpInternal convert(IpExternal ip) {
        return IpInternal.builder()
                .status(ip.getStatus())
                .message(ip.getMessage())
                .continent(ip.getContinent())
                .continentCode(ip.getContinentCode())
                .country(ip.getCountry())
                .countryCode(ip.getCountryCode())
                .region(ip.getRegion())
                .regionName(ip.getRegionName())
                .city(ip.getCity())
                .district(ip.getDistrict())
                .zip(ip.getZip())
                .lat(ip.getLat())
                .lon(ip.getLon())
                .timezone(ip.getTimezone())
                .currency(ip.getCurrency())
                .isp(ip.getIsp())
                .organizationName(ip.getOrganizationName())
                .as(ip.getAs())
                .asname(ip.getAsname())
                .reverse(ip.getReverse())
                .mobile(ip.isMobile())
                .proxy(ip.isProxy())
                .hosting(ip.isHosting())
                .query(ip.getQuery())
                .build();
    }
}
