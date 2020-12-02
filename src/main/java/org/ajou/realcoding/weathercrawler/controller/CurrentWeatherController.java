package org.ajou.realcoding.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.service.CurrentWeatherService;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CurrentWeatherController {

    @Autowired
    private CurrentWeatherService currentWeatherService;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/weather-service/weathers")
    public CurrentWeather getCurrentWeather(@RequestParam String cityName) {
        return currentWeatherService.getCurrentWeather(cityName);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/weather-service/available-cities")
    public List<String> getAvailableCities() throws IOException {
        return currentWeatherService.getAvailableCities();
    }
}
