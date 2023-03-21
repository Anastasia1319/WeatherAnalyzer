package com.oleshko.weatherAnalyzer.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherInfoDto {

    private Long id;

    private String country;

    private String locality;

    private LocalDateTime dateTime;

    private Double temperatureDegreesC;

    private Double windSpeedMetersPerSecond;

    private Double atmosphericPressureHPa;

    private Integer airHumidity;

    private String weatherCondition;
}
