package com.oleshko.weatherAnalyzer.data.repository;

import com.oleshko.weatherAnalyzer.data.entity.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {

    Optional<WeatherInfo> findFirstByOrderByDateTimeDesc();

    @Query("from WeatherInfo w where w.dateTime between :from and :to")
    List<WeatherInfo> findAverageTemperature(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
