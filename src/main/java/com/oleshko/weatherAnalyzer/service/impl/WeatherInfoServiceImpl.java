package com.oleshko.weatherAnalyzer.service.impl;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import com.oleshko.weatherAnalyzer.data.repository.WeatherInfoRepository;
import com.oleshko.weatherAnalyzer.exception.NotFoundException;
import com.oleshko.weatherAnalyzer.service.WeatherInfoService;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherInfoServiceImpl implements WeatherInfoService {
    private final WeatherInfoRepository weatherInfoRepository;

    private final ConverterService converter;

    @Override
    public WeatherInfoDto getActualWeather() {
        WeatherInfo weatherInfo = weatherInfoRepository.findFirstByOrderByDateTimeDesc()
                .orElseThrow(() -> new NotFoundException("Actual information about weather not found"));
        return converter.toWeatherInfoDto(weatherInfo);
    }

    @Override
    public WeatherInfoDto save(WeatherInfoDto weatherInfoDto) {
        WeatherInfo saved = weatherInfoRepository.saveAndFlush(converter.toWeatherInfoEntity(weatherInfoDto));
        return converter.toWeatherInfoDto(saved);
    }
}
