package com.distributedsystems.restfulserviceshomework.controller.html;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServiceChoiceHtmlController {

    @RequestMapping("/")
    public String servicesList() {
        return "services_list";
    }
}