package com.distributedsystems.restfulserviceshomework.controller.html;

import com.distributedsystems.restfulserviceshomework.model.Location;
import com.distributedsystems.restfulserviceshomework.request.TemperatureDateRangeRequest;
import com.distributedsystems.restfulserviceshomework.request.TemperatureSingleDayRequest;
import com.distributedsystems.restfulserviceshomework.response.WeatherResponse;
import com.distributedsystems.restfulserviceshomework.service.LocationService;
import com.distributedsystems.restfulserviceshomework.service.WeatherProvidingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/statistics/temperature")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherHtmlController {

    private LocationService locationService;
    private WeatherProvidingService weatherProvidingService;

    @GetMapping("/singleDay")
    public String getSingleDayTemperatureForm(Model model) {
        model.addAttribute("request", new TemperatureSingleDayRequest());
        return "temperature_single_day_form";
    }

    @GetMapping("/dateRange")
    public String getDateRangeTemperatureForm(Model model) {
        model.addAttribute("request", new TemperatureDateRangeRequest());
        return "temperature_date_range_form";
    }

    @GetMapping("/temperatureResultSingleDay")
    public String showTemperatureResultForOneDay(@RequestParam String locationName,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                 Model model) {
        final Location location = locationService.getFirstFoundLocation(locationName);
        WeatherResponse response = weatherProvidingService.getWeatherForSingleDay(location, date);

        model.addAttribute("city", response.getCity());
        model.addAttribute("averageTemperature", response.getTemperature().getAverage());
        model.addAttribute("minimumTemperature", response.getTemperature().getMinimum());
        model.addAttribute("maximumTemperature", response.getTemperature().getMaximum());
        return "temperature_single_day_result_form";
    }

    @GetMapping("/temperatureResultDateRange")
    public String showTemperatureResultForDateRange(@RequestParam String locationName,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                    Model model) {
        final Location location = locationService.getFirstFoundLocation(locationName);
        WeatherResponse response = weatherProvidingService.getWeatherForDateRange(location, startDate, endDate);
        model.addAttribute("city", response.getCity());
        model.addAttribute("averageTemperature", response.getTemperature().getAverage());
        model.addAttribute("minimumTemperature", response.getTemperature().getMinimum());
        model.addAttribute("maximumTemperature", response.getTemperature().getMaximum());
        return "temperature_date_range_result_form";
    }
}
