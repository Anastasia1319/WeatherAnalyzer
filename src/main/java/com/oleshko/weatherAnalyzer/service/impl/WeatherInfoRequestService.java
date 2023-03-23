package com.oleshko.weatherAnalyzer.service.impl;

import com.oleshko.weatherAnalyzer.exception.NotConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherInfoRequestService {

    @Value("${data.api.uri}")
    private String uri;

    @Value("${data.api.X-RapidAPI-Key}")
    private String headerApiKey;

    @Value("${data.api.X-RapidAPI-Host}")
    private String headerApiHost;

    @Value("${data.api.method}")
    private String method;

    @Value("${data.api.city}")
    private String city;

    public String getApiConnection() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "?q=" + city))
                .header("X-RapidAPI-Key", headerApiKey)
                .header("X-RapidAPI-Host", headerApiHost)
                .method(method, HttpRequest.BodyPublishers.noBody())
                .build();
        log.info("HttpRequest was created");
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new NotConnectionException(e);
        }
        log.info("Response was got");
        return response.body();
    }
}
