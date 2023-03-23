package com.oleshko.weatherAnalyzer.web;

import com.oleshko.weatherAnalyzer.service.WeatherInfoService;
import com.oleshko.weatherAnalyzer.service.dto.AverageTemperatureDto;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weatherInfo")
public class WeatherInfoController {

    private final WeatherInfoService weatherInfoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public WeatherInfoDto get() {
        return weatherInfoService.getActualWeather();
    }

    @PostMapping("/average")
    @ResponseStatus(HttpStatus.OK)
    public AverageTemperatureDto getAverage(@RequestBody LocalDate dateFrom, @RequestBody LocalDate dateTo) {
        return weatherInfoService.getAverageTemperature(dateFrom, dateTo);
    }
}
