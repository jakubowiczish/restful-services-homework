package com.distributedsystems.restfulserviceshomework.controller.ip.rest;

import com.distributedsystems.restfulserviceshomework.model.ip.internal.IpInternal;
import com.distributedsystems.restfulserviceshomework.service.ip.IpService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ip")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IpRestController {

    private IpService ipService;

    @GetMapping("/singleIp/{ipAddresses}")
    public ResponseEntity<List<IpInternal>> getSingleIpInfo(@PathVariable List<String> ipAddresses) {
        return ResponseEntity.ok(ipService.getIpAddressesInfo(ipAddresses));
    }
}
