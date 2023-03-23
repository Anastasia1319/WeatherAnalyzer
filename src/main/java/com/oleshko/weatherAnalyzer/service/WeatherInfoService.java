package com.oleshko.weatherAnalyzer.service;

import com.oleshko.weatherAnalyzer.service.dto.AverageTemperatureDto;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;

import java.time.LocalDate;

public interface WeatherInfoService {
    WeatherInfoDto getActualWeather();

    void save(WeatherInfoDto weatherInfoDto);

    AverageTemperatureDto getAverageTemperature(LocalDate dateFrom, LocalDate dateTo);
}
