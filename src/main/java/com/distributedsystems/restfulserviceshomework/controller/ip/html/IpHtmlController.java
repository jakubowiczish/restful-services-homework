package com.distributedsystems.restfulserviceshomework.controller.ip.html;

import com.distributedsystems.restfulserviceshomework.model.ip.internal.IpInternalResponse;
import com.distributedsystems.restfulserviceshomework.model.ip.internal.IpRequest;
import com.distributedsystems.restfulserviceshomework.service.ip.IpService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/ips")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class IpHtmlController {

    private IpService ipService;

    @GetMapping("/getInfo")
    public String getIpAddresses(Model model) {
        model.addAttribute("request", new IpRequest());
        return "get_ip_info_form";
    }

    @GetMapping("/ipInfoResult")
    public String showIpResultInfo(@RequestParam String ipAddresses,
                                   @RequestParam String city,
                                   @RequestParam String countryCode,
                                   @RequestParam String continentCode,
                                   @RequestParam String organizationName,
                                   Model model) {

        final List<String> ipAddressesList = List.of(ipAddresses.split(","));
        final List<IpInternalResponse> ipAddressesInfo
                = ipService.getIpAddressesInfo(ipAddressesList, city, countryCode, continentCode, organizationName);

        model.addAttribute("ips", ipAddressesInfo);
        return "get_ip_info_result_form";
    }
}
