package com.distributedsystems.restfulserviceshomework.service.ip;

import com.distributedsystems.restfulserviceshomework.model.ip.external.IpExternalResponse;
import com.distributedsystems.restfulserviceshomework.model.ip.internal.IpInternalResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static com.distributedsystems.restfulserviceshomework.util.Constants.IP_API_BASE_URL;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Service
public class IpService {

    public List<IpInternalResponse> getIpAddressesInfo(final List<String> ipAddresses,
                                                       final String city,
                                                       final String countryCode,
                                                       final String continentCode,
                                                       final String organizationName) {
        return ipAddresses.stream()
                .map(this::getSingleIpInfo)
                .filter(getCityPredicate(city))
                .filter(getCountryCodePredicate(countryCode))
                .filter(getContinentCodePredicate(continentCode))
                .filter(getOrganizationNamePredicate(organizationName))
                .collect(toList());
    }

    private IpInternalResponse getSingleIpInfo(final String ipAddress) {
        return convert(requireNonNull(new RestTemplate()
                .getForEntity(getIpUri(ipAddress), IpExternalResponse.class)
                .getBody()));
    }

    private String getIpUri(final String ipAddress) {
        return UriComponentsBuilder
                .fromHttpUrl(IP_API_BASE_URL + "/{ipAddress}?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,hosting,query")
                .buildAndExpand(ipAddress)
                .toUriString();
    }

    private Predicate<IpInternalResponse> getCityPredicate(final String city) {
        return StringUtils.isEmpty(city)
                ? e -> true
                : e -> Objects.equals(e.getCity(), city);
    }

    private Predicate<IpInternalResponse> getCountryCodePredicate(final String countryCode) {
        return StringUtils.isEmpty(countryCode)
                ? e -> true
                : e -> Objects.equals(e.getCountryCode(), countryCode);
    }

    private Predicate<IpInternalResponse> getContinentCodePredicate(final String continentCode) {
        return StringUtils.isEmpty(continentCode)
                ? e -> true
                : e -> Objects.equals(e.getContinentCode(), continentCode);
    }

    private Predicate<IpInternalResponse> getOrganizationNamePredicate(final String organizationName) {
        return StringUtils.isEmpty(organizationName)
                ? e -> true
                : e -> e.getOrganizationName().toLowerCase().contains(organizationName.toLowerCase());
    }

    private IpInternalResponse convert(final IpExternalResponse ip) {
        return IpInternalResponse.builder()
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
