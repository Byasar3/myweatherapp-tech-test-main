package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  String address;

  @JsonProperty("description")
  String description;

  @JsonProperty("currentConditions")
  CurrentConditions currentConditions;

  @JsonProperty("days")
  List<Days> days;

  static class CurrentConditions {
    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("sunrise")
    String sunrise;

    @JsonProperty("sunset")
    String sunset;

    @JsonProperty("feelslike")
    String feelslike;

    @JsonProperty("humidity")
    String humidity;

    @JsonProperty("conditions")
    String conditions;

    public String getSunrise() {
      return sunrise;
    }

    public String getSunset() {
      return sunset; 
    }

    public String getConditions() {
      return conditions; 
    }

  }
  static class Days {

    @JsonProperty("datetime")
    String date;

    @JsonProperty("temp")
    String currentTemperature;

    @JsonProperty("tempmax")
    String maxTemperature;

    @JsonProperty("tempmin")
    String minTemperature;

    @JsonProperty("conditions")
    String conditions;

    @JsonProperty("description")
    String description;

  }

  public String getAddress() {
    return address;
  }

  public String getDescription() {
    return description;
  }

  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  }

  public List<Days> getDays() {
    return days;
  }
  
  public String getSunrise() {
    if (currentConditions != null) {
      return currentConditions.getSunrise();
    }
    return null;
  }

  public String getSunset() {
    if (currentConditions != null) {
      return currentConditions.getSunset();
    }
    return null;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCondition() {
    if (currentConditions != null) {
      return currentConditions.getConditions();
    }
    return null;
  }

}
