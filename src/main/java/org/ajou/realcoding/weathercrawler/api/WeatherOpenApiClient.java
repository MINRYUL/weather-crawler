package org.ajou.realcoding.weathercrawler.api;


import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherOpenApiClient {

    @Autowired
    private RestTemplate restTemplate;
    private static final String WEATHER_REQUEST_URI = "http://api.openweathermap.org/data/2.5/weather?q={cityName}&appid=365e807154ee40fe752a0bffa0474083";

    public CurrentWeather getCurrentWeather(String cityName) {
        CurrentWeather currentWeather = restTemplate.getForObject(WEATHER_REQUEST_URI, CurrentWeather.class, cityName);
        return currentWeather;
    }
}
