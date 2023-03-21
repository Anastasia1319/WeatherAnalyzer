package com.oleshko.weatherAnalyzer.service;

import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;

public interface WeatherInfoService {
    WeatherInfoDto getActualWeather();

    WeatherInfoDto save(WeatherInfoDto weatherInfoDto);
}
