package com.distributedsystems.restfulserviceshomework.controller.ip.rest;

import com.distributedsystems.restfulserviceshomework.model.ip.internal.IpInternalResponse;
import com.distributedsystems.restfulserviceshomework.service.ip.IpService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ip")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IpRestController {

    private IpService ipService;

    @GetMapping("/ipInfo/{ipAddresses}")
    public ResponseEntity<List<IpInternalResponse>> getSingleIpInfo(@PathVariable List<String> ipAddresses,
                                                                    @RequestParam(required = false) String city,
                                                                    @RequestParam(required = false) String countryCode,
                                                                    @RequestParam(required = false) String continentCode,
                                                                    @RequestParam(required = false) String organizationName) {
        return ResponseEntity.ok(ipService.getIpAddressesInfo(ipAddresses, city, countryCode, continentCode, organizationName));
    }
}
