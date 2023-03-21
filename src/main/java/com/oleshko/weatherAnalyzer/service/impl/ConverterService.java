package com.oleshko.weatherAnalyzer.service.impl;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterService {

    public WeatherInfoDto toWeatherInfoDto(WeatherInfo weatherInfo) {
        WeatherInfoDto weatherInfoDto = new WeatherInfoDto();
        weatherInfoDto.setId(weatherInfo.getId());
        weatherInfoDto.setCountry(weatherInfo.getCountry());
        weatherInfoDto.setLocality(weatherInfo.getLocality());
        weatherInfoDto.setDateTime(weatherInfo.getDateTime());
        weatherInfoDto.setTemperatureDegreesC(weatherInfo.getTemperatureDegreesC());
        weatherInfoDto.setWindSpeedMetersPerSecond(weatherInfo.getWindSpeedMetersPerSecond());
        weatherInfoDto.setAtmosphericPressureHPa(weatherInfo.getAtmosphericPressureHPa());
        weatherInfoDto.setAirHumidity(weatherInfo.getAirHumidity());
        weatherInfoDto.setWeatherCondition(weatherInfo.getWeatherCondition());
        return weatherInfoDto;
    }

    public WeatherInfo toWeatherInfoEntity(WeatherInfoDto weatherInfoDto) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setId(weatherInfoDto.getId());
        weatherInfo.setCountry(weatherInfoDto.getCountry());
        weatherInfo.setLocality(weatherInfoDto.getLocality());
        weatherInfo.setDateTime(weatherInfoDto.getDateTime());
        weatherInfo.setTemperatureDegreesC(weatherInfoDto.getTemperatureDegreesC());
        weatherInfo.setWindSpeedMetersPerSecond(weatherInfoDto.getWindSpeedMetersPerSecond());
        weatherInfo.setAtmosphericPressureHPa(weatherInfoDto.getAtmosphericPressureHPa());
        weatherInfo.setAirHumidity(weatherInfoDto.getAirHumidity());
        weatherInfo.setWeatherCondition(weatherInfoDto.getWeatherCondition());
        return weatherInfo;
    }
}
