package com.oleshko.weatherAnalyzer.service.parser;

import com.oleshko.weatherAnalyzer.service.dto.WeatherInfoDto;

public interface Parser {
    WeatherInfoDto parse();
}
