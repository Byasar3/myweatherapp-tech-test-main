package com.weatherapp.myweatherapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.weatherapp.myweatherapp.model.CityInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherControllerTest {

    @Test
    void testCompareDaylightHours() {
        WeatherController weatherController = new WeatherController();

        String city1 = "London";
        String city2 = "New York";

        ResponseEntity<CityInfo> response = weatherController.compareDaylightHours(city1, city2);

        assertEquals("London has the longest day.", response.getBody().getDescription());
    }

    @Test
    void testGetCurrentRainCity() {
        WeatherController weatherController = new WeatherController();

        String city1 = "London";
        String city2 = "New York";

        ResponseEntity<String> response = weatherController.getCurrentRainCity(city1, city2);

        assertEquals("London is currently experiencing rain.", response.getBody());
    }

}
