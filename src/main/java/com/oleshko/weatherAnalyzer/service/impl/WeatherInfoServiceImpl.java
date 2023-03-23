package com.oleshko.weatherAnalyzer.service.impl;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import com.oleshko.weatherAnalyzer.data.repository.WeatherInfoRepository;
import com.oleshko.weatherAnalyzer.exception.NotFoundException;
import com.oleshko.weatherAnalyzer.service.WeatherInfoService;
import com.oleshko.weatherAnalyzer.service.dto.AverageTemperatureDto;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import com.oleshko.weatherAnalyzer.service.parser.impl.ParserWeatherInfoFromApiImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherInfoServiceImpl implements WeatherInfoService {
    private final WeatherInfoRepository weatherInfoRepository;

    private final ParserWeatherInfoFromApiImpl parserWeatherInfoFromApi;

    private final ConverterService converter;

    @Override
    public WeatherInfoDto getActualWeather() {
        getWeatherInfoFromApi();
        WeatherInfo weatherInfo = weatherInfoRepository.findFirstByOrderByDateTimeDesc()
                .orElseThrow(() -> new NotFoundException("Actual information about weather not found"));
        return converter.toWeatherInfoDto(weatherInfo);
    }

    @Override
    public void save(WeatherInfoDto weatherInfoDto) {
        WeatherInfo saved = weatherInfoRepository.save(converter.toWeatherInfoEntity(weatherInfoDto));
        converter.toWeatherInfoDto(saved);
    }

    @Override
    public AverageTemperatureDto getAverageTemperature(LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime dateTimeFrom = dateFrom.atTime(0, 0);
        LocalDateTime dateTimeTo = dateTo.atTime(23, 59);
        List<WeatherInfo> weatherInfoList = weatherInfoRepository.findAverageTemperature(dateTimeFrom, dateTimeTo);
        Double averageTemperature = foundDayAverageTemperature(weatherInfoList);
        return AverageTemperatureDto.builder()
                .averageTemperature(averageTemperature)
                .build();
    }

    @Async
    @Scheduled(cron = "${data.api.timetable}")
    public void getWeatherInfoFromApi() {
        WeatherInfoDto weatherInfoDto = parserWeatherInfoFromApi.parse();
        if (weatherInfoDto != null) {
            save(weatherInfoDto);
        }
    }

    private Double foundDayAverageTemperature(List<WeatherInfo> weatherInfoList) {
        return weatherInfoList.stream()
                .mapToDouble(weather -> weather.getTemperatureDegreesC())
                .average()
                .orElseThrow(() -> new NotFoundException("Invalid date range"));
    }
}
