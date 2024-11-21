package com.zohu.weatherapp.controller;

import com.zohu.weatherapp.model.Weather;
import com.zohu.weatherapp.repo.WeatherRepository;
import com.zohu.weatherapp.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherRepository weatherRepository;

    public WeatherController(WeatherService weatherService, WeatherRepository weatherRepository) {
        this.weatherService = weatherService;
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/{city}")
    public ResponseEntity<Weather> getWeather(@PathVariable String city) {
        Weather weather = weatherService.fetchWeather(city);
        weatherRepository.save(weather); // Save to DB
        return ResponseEntity.ok(weather);
    }
}
