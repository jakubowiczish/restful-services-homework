package com.distributedsystems.restfulserviceshomework.controller.weather.html;

import com.distributedsystems.restfulserviceshomework.model.weather.internal.LocationInternal;
import com.distributedsystems.restfulserviceshomework.model.weather.internal.TemperatureDateRangeRequest;
import com.distributedsystems.restfulserviceshomework.model.weather.internal.TemperatureSingleDayRequest;
import com.distributedsystems.restfulserviceshomework.model.weather.internal.WeatherResponse;
import com.distributedsystems.restfulserviceshomework.service.weather.LocationService;
import com.distributedsystems.restfulserviceshomework.service.weather.WeatherService;
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
@RequestMapping("/statistics/weather")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherHtmlController {

    private LocationService locationService;
    private WeatherService weatherService;

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

    @GetMapping("/weatherSingleDay")
    public String showTemperatureResultForOneDay(@RequestParam String locationName,
                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                 Model model) {
        final LocationInternal locationInternal = locationService.getFirstFoundLocation(locationName);
        WeatherResponse response = weatherService.getWeatherForSingleDay(locationInternal, date);
        addAttributesToModel(response, model);
        return "temperature_single_day_result_form";
    }

    @GetMapping("/weatherDateRange")
    public String showTemperatureResultForDateRange(@RequestParam String locationName,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                    Model model) {
        final LocationInternal locationInternal = locationService.getFirstFoundLocation(locationName);
        WeatherResponse response = weatherService.getWeatherForDateRange(locationInternal, startDate, endDate);
        addAttributesToModel(response, model);
        return "temperature_date_range_result_form";
    }

    private void addAttributesToModel(WeatherResponse response, Model model) {
        model.addAttribute("information", response.getInformation());
        model.addAttribute("city", response.getCity());

        model.addAttribute("minimumTemperature", response.getTemperature().getMinimum());
        model.addAttribute("averageTemperature", response.getTemperature().getAverage());
        model.addAttribute("maximumTemperature", response.getTemperature().getMaximum());
        model.addAttribute("temperatureUnit", response.getTemperature().getUnit());

        model.addAttribute("minimumAirPressure", response.getAirPressure().getMinimum());
        model.addAttribute("averageAirPressure", response.getAirPressure().getAverage());
        model.addAttribute("maximumAirPressure", response.getAirPressure().getMaximum());
        model.addAttribute("airPressureUnit", response.getAirPressure().getUnit());

        model.addAttribute("minimumWind", response.getWind().getMinimum());
        model.addAttribute("averageWind", response.getWind().getAverage());
        model.addAttribute("maximumWind", response.getWind().getMaximum());
        model.addAttribute("windUnit", response.getWind().getUnit());

        model.addAttribute("minimumHumidity", response.getHumidity().getMinimum());
        model.addAttribute("averageHumidity", response.getHumidity().getAverage());
        model.addAttribute("maximumHumidity", response.getHumidity().getMaximum());
        model.addAttribute("humidityUnit", response.getHumidity().getUnit());

        model.addAttribute("minimumVisibility", response.getVisibility().getMinimum());
        model.addAttribute("averageVisibility", response.getVisibility().getAverage());
        model.addAttribute("maximumVisibility", response.getVisibility().getMaximum());
        model.addAttribute("visibilityUnit", response.getVisibility().getUnit());
    }
}
