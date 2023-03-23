package com.oleshko.weatherAnalyzer.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "weather_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "locality", length = 200)
    private String locality;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "temperature_c")
    private Double temperatureDegreesC;

    @Column(name = "wind_speed_m_in_s")
    private Double windSpeedMetersPerSecond;

    @Column(name = "atmospheric_pressure_hpa")
    private Double atmosphericPressureHPa;

    @Column(name = "air_humidity")
    private Integer airHumidity;

    @Column(name = "weather_condition", length = 100)
    private String weatherCondition;

}
