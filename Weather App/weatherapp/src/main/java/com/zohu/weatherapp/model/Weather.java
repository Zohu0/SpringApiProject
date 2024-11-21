package com.zohu.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.Main;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private String temperature;
    private String humidity;
    private String max_temp;
    private String min_temp;
    private String feelsLike;
    private String description;
    private LocalDateTime timestamp;

    // Getters and Setters

    // Add inner classes to map the response
    public static class Main {
        private String temp;
        private String humidity;
        @JsonProperty("temp_max")
        private String temp_max;
        @JsonProperty("temp_min")
        private String temp_min;
        @JsonProperty("feels_like")
        private String feels_like;

        // Getters and Setters
        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getMaxTemp() {
            return temp_max;
        }

        public void setMaxTemp(String temp_max) {
            this.temp_max = temp_max;
        }

        public String getMinTemp() {
            return temp_min;
        }

        public void setMinTemp(String temp_min) {
            this.temp_min = temp_min;
        }

        public String getFeelsLike() {
            return feels_like;
        }

        public void setFeelsLike(String feels_like) {
            this.feels_like = feels_like;
        }

    }





    public static class WeatherDescription {
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }


    // Inner model for the API response
    public static class WeatherResponse {
        private List<WeatherDescription> weather;
        private Main main;

        public List<WeatherDescription> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherDescription> weather) {
            this.weather = weather;
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }
    }
}
