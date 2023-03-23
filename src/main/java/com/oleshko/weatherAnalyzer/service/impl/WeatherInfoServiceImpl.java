package com.oleshko.weatherAnalyzer.service.impl;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import com.oleshko.weatherAnalyzer.data.repository.WeatherInfoRepository;
import com.oleshko.weatherAnalyzer.exception.NotFoundException;
import com.oleshko.weatherAnalyzer.service.WeatherInfoService;
import com.oleshko.weatherAnalyzer.service.dto.AverageTemperatureDto;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import com.oleshko.weatherAnalyzer.service.parser.impl.ParserWeatherInfoFromApiImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherInfoServiceImpl implements WeatherInfoService {
    private final WeatherInfoRepository weatherInfoRepository;

    private final ParserWeatherInfoFromApiImpl parserWeatherInfoFromApi;

    private final ConverterService converter;

    @Override
    public WeatherInfoDto getActualWeather() {
        log.info("Trying get actual weather");
        getWeatherInfoFromApi();
        WeatherInfo weatherInfo = weatherInfoRepository.findFirstByOrderByDateTimeDesc()
                .orElseThrow(() -> new NotFoundException("Actual information about weather not found"));
        log.info("Actual weather information was got");
        return converter.toWeatherInfoDto(weatherInfo);
    }

    @Override
    public void save(WeatherInfoDto weatherInfoDto) {
        WeatherInfo saved = weatherInfoRepository.save(converter.toWeatherInfoEntity(weatherInfoDto));
        converter.toWeatherInfoDto(saved);
        log.info("Actual weather information was got from API");
    }

    @Override
    public AverageTemperatureDto getAverageTemperature(LocalDate dateFrom, LocalDate dateTo) {
        log.info("Trying get average temperature from {} to {}", dateFrom, dateTo);
        LocalDateTime dateTimeFrom = dateFrom.atTime(0, 0);
        LocalDateTime dateTimeTo = dateTo.atTime(23, 59);
        List<WeatherInfo> weatherInfoList = weatherInfoRepository.findAverageTemperature(dateTimeFrom, dateTimeTo);
        DecimalFormat format = new DecimalFormat("#.###");
        Double averageTemperature = Double.valueOf(format.format(foundDayAverageTemperature(weatherInfoList)));
        log.info("Average temperature was calculate");
        return AverageTemperatureDto.builder()
                .averageTemperature(averageTemperature)
                .build();
    }

    @Async
    @Scheduled(cron = "${data.api.timetable}")
    public void getWeatherInfoFromApi() {
        log.info("Obtaining weather information from API after a specified period of time");
        WeatherInfoDto weatherInfoDto = parserWeatherInfoFromApi.parse();
        if (weatherInfoDto != null) {
            save(weatherInfoDto);
        }
    }

    private Double foundDayAverageTemperature(List<WeatherInfo> weatherInfoList) {
        log.info("Trying calculate average temperature");
        return weatherInfoList.stream()
                .mapToDouble(weather -> weather.getTemperatureDegreesC())
                .average()
                .orElseThrow(() -> new NotFoundException("Invalid date range"));
    }
}
