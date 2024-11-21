package com.zohu.weatherapp.service;

import com.zohu.weatherapp.customexceptions.CityNotFoundException;
import com.zohu.weatherapp.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Weather fetchWeather(String cityName) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey + "&units=metric";

        try {
            ResponseEntity<Weather.WeatherResponse> response = restTemplate.getForEntity(url, Weather.WeatherResponse.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Weather.WeatherResponse weatherResponse = response.getBody();

                // Extract weather data
                String cityTemp = weatherResponse.getMain().getTemp();
                String maxTemp = weatherResponse.getMain().getMaxTemp();
                String minTemp = weatherResponse.getMain().getMinTemp();
                String cityDesc = weatherResponse.getWeather().get(0).getDescription();
                LocalDateTime cityTime = LocalDateTime.now();
                String cityHumidity = weatherResponse.getMain().getHumidity();
                String feelsLike = weatherResponse.getMain().getFeelsLike();

                // Create and populate Weather object
                Weather weather = new Weather();
                weather.setCityName(cityName);
                weather.setTemperature(cityTemp);
                weather.setDescription(cityDesc);
                weather.setHumidity(cityHumidity);
                weather.setTimestamp(cityTime);
                weather.setMax_temp(maxTemp);
                weather.setMin_temp(minTemp);
                weather.setFeelsLike(feelsLike);

                return weather;
            } else {
                throw new RuntimeException("Unexpected response from weather API: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new CityNotFoundException("City not found: " + cityName);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("HTTP error while fetching weather data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
    }
}
