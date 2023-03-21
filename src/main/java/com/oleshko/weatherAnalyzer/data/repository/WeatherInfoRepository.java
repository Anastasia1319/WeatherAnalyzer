package com.oleshko.weatherAnalyzer.data.repository;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {

    Optional<WeatherInfo> findFirstByOrderByDateTimeDesc();

}
