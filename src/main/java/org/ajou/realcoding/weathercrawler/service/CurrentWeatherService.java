package org.ajou.realcoding.weathercrawler.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ajou.realcoding.weathercrawler.api.WeatherOpenApiClient;
import org.ajou.realcoding.weathercrawler.domain.City;
import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.repository.CurrentWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class CurrentWeatherService {

    @Autowired
    private WeatherOpenApiClient weatherOpenApiClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CurrentWeatherRepository currentWeatherRepository;

    private Queue<String> cityNameQueue = new LinkedList<>();

    @PostConstruct
    public void setUpCityNames() throws IOException {
        List<String> availableCityNames = this.getAvailableCities();
        cityNameQueue.addAll(availableCityNames);
    }

    public List<String> getAvailableCities() throws IOException {
        List<City> cities = objectMapper.readValue(new File("city.list.json"),
                new TypeReference<List<City>>() {
                });

//        List<String> toBeReturnCityNames = new ArrayList<>();
//        for(City city : cities){
//            if (city.getCountry().equals("KR")){
//                toBeReturnCityNames.add(city.getName());
//            }
//        }
//        return toBeReturnCityNames;


        return cities.stream()
                .filter(city -> city.getCountry().equals("KR"))
                .map(city -> city.getName())
                .collect(Collectors.toList());

    }

    public CurrentWeather getCurrentWeather(String cityName) {
      //  return weatherOpenApiClient.getCurrentWeather(cityName);
        return currentWeatherRepository.findCurrentWeather(cityName);
    }

    @Scheduled(fixedDelay = 2000L)
    public void getCurrentWeatherEveryTwoSeconds() {
        String targetCityName = cityNameQueue.poll();
        cityNameQueue.add(targetCityName);

        CurrentWeather currentWeather = weatherOpenApiClient.getCurrentWeather(targetCityName);
        currentWeatherRepository.saveCurrentWeather(currentWeather);
    }
}
