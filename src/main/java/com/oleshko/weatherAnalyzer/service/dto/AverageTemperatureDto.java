package com.oleshko.weatherAnalyzer.service.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AverageTemperatureDto {

    @JsonProperty("average_temp")
    private Double averageTemperature;
}
