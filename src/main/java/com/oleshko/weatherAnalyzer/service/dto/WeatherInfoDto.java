package com.oleshko.weatherAnalyzer.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
