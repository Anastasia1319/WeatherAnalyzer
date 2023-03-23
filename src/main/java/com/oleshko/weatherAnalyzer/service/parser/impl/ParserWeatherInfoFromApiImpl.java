package com.oleshko.weatherAnalyzer.service.parser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;
import com.oleshko.weatherAnalyzer.service.impl.WeatherInfoRequestService;
import com.oleshko.weatherAnalyzer.service.parser.Parser;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Data
@Slf4j
public class ParserWeatherInfoFromApiImpl implements Parser {
    private final WeatherInfoRequestService weatherInfoRequest;

    @Override
    public WeatherInfoDto parse() {
        log.info("Trying parsing the page from API source");
        String dataFromApi = weatherInfoRequest.getApiConnection();
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherInfoDto weatherInfoDto;
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(dataFromApi);
            JsonNode location = jsonNode.get("location");
            JsonNode current = jsonNode.get("current");

            weatherInfoDto = WeatherInfoDto.builder()
                    .country(location.get("country").asText())
                    .locality(location.get("region").asText())
                    .dateTime(LocalDateTime.parse(location.get("localtime").asText(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm")))
                    .temperatureDegreesC(current.get("temp_c").asDouble())
                    .windSpeedMetersPerSecond(current.get("wind_mph").asDouble())
                    .atmosphericPressureHPa(current.get("pressure_mb").asDouble())
                    .airHumidity(current.get("humidity").asInt())
                    .weatherCondition(current.get("cloud").asText())
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        log.info("The page from API source was parse");
        return weatherInfoDto;
    }
}
