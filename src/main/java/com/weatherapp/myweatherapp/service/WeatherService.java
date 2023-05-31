package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }

  public LocalTime getSunriseTime(String city) {

    CityInfo cityInfo = weatherRepo.getByCity(city);
    String sunriseTimeString = cityInfo.getSunrise();

    // parse sunrise time string to localtime
    LocalTime sunriseTime = LocalTime.parse(sunriseTimeString);

    return sunriseTime;
  }

  public LocalTime getSunsetTime(String city) {
    CityInfo cityInfo = weatherRepo.getByCity(city);
    String sunsetTimeString = cityInfo.getSunset();

    // parse sunset time string to localtime
    LocalTime sunsetTime = LocalTime.parse(sunsetTimeString);

    return sunsetTime;
  }

  public boolean hasRain(String city) {
    CityInfo cityInfo = weatherRepo.getByCity(city);

    if (cityInfo != null && cityInfo.getCurrentConditions() != null) {
      String conditions = cityInfo.getCondition();
      return conditions != null && conditions.toLowerCase().contains("rain");
    }
    return false;
  }
}
