package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import java.time.Duration;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }

  public static Duration calculateDaylightDuration(LocalTime sunriseTime, LocalTime sunsetTime) {
    if (sunsetTime.isBefore(sunriseTime)) {
      // If the sunset time is before the sunrise time, it means the sunset is on the next day
      sunsetTime = sunsetTime.plusHours(24);
    }

    return Duration.between(sunriseTime, sunsetTime);
  }

  // TODO: given two city names, compare the length of the daylight hours and return the city with the longest day

  @GetMapping("/daylight-hours-comparison")
  public ResponseEntity<CityInfo> compareDaylightHours(@RequestParam("city1") String city1, @RequestParam("city2") String city2) {
    // get the sunrise and sunset hours of the two cities
    LocalTime city1SunriseTime = weatherService.getSunriseTime(city1);
    LocalTime city1SunsetTime = weatherService.getSunsetTime(city1);

    LocalTime city2SunriseTime = weatherService.getSunriseTime(city2);
    LocalTime city2SunsetTime = weatherService.getSunsetTime(city2);

    // calculate the daylight hours for city 1 and city2
    Duration city1DaylightDuration = calculateDaylightDuration(city1SunriseTime, city1SunsetTime);

    Duration city2DaylightDuration = calculateDaylightDuration(city2SunriseTime, city2SunsetTime);

    // Output the calculated daylight hours to the console
    System.out.println("City 1 daylight duration: " + city1DaylightDuration);
    System.out.println("City 2 daylight duration: " + city2DaylightDuration);

    // Update the CityInfo object with the city that has the longest day
    CityInfo cityInfo = new CityInfo();
    if (city1DaylightDuration.compareTo(city2DaylightDuration) > 0) {
      cityInfo.setDescription(city1 + " has the longest day.");
    } else if (city1DaylightDuration.compareTo(city2DaylightDuration) < 0) {
      cityInfo.setDescription(city2 + " has the longest day.");
    } else {
      cityInfo.setDescription("Both cities have the same daylight duration.");
    }  
    System.out.println(cityInfo);
    // return the description 
    return ResponseEntity.ok(cityInfo);  
    
  }


  // TODO: given two city names, check which city its currently raining in

}
