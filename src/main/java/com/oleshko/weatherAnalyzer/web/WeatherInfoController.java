package com.oleshko.weatherAnalyzer.web;

import com.oleshko.weatherAnalyzer.service.WeatherInfoService;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weatherInfo")
public class WeatherInfoController {

    private final WeatherInfoService weatherInfoService;

    @GetMapping
    public WeatherInfoDto get() {
        return weatherInfoService.getActualWeather();
    }
}
