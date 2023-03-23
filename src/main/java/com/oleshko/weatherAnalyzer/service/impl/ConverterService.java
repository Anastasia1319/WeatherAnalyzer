package com.oleshko.weatherAnalyzer.service.impl;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConverterService {

    public WeatherInfoDto toWeatherInfoDto(WeatherInfo weatherInfo) {
        log.info("Transform method called from WeatherInfo to WeatherInfoDto");
        return WeatherInfoDto.builder()
                .id(weatherInfo.getId())
                .country(weatherInfo.getCountry())
                .locality(weatherInfo.getLocality())
                .dateTime(weatherInfo.getDateTime())
                .temperatureDegreesC(weatherInfo.getTemperatureDegreesC())
                .windSpeedMetersPerSecond(weatherInfo.getWindSpeedMetersPerSecond())
                .atmosphericPressureHPa(weatherInfo.getAtmosphericPressureHPa())
                .airHumidity(weatherInfo.getAirHumidity())
                .weatherCondition(weatherInfo.getWeatherCondition())
                .build();
    }

    public WeatherInfo toWeatherInfoEntity(WeatherInfoDto weatherInfoDto) {
        log.info("Transform method called from WeatherInfoDto to WeatherInfo");
        return WeatherInfo.builder()
                .id(weatherInfoDto.getId())
                .country(weatherInfoDto.getCountry())
                .locality(weatherInfoDto.getLocality())
                .dateTime(weatherInfoDto.getDateTime())
                .temperatureDegreesC(weatherInfoDto.getTemperatureDegreesC())
                .windSpeedMetersPerSecond(weatherInfoDto.getWindSpeedMetersPerSecond())
                .atmosphericPressureHPa(weatherInfoDto.getAtmosphericPressureHPa())
                .airHumidity(weatherInfoDto.getAirHumidity())
                .weatherCondition(weatherInfoDto.getWeatherCondition())
                .build();
    }
}
