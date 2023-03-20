package com.oleshko.weatherAnalyzer.data.repository;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
}
