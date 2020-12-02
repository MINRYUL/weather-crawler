package org.ajou.realcoding.weathercrawler.repository;

import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class CurrentWeatherRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveCurrentWeather(CurrentWeather currentWeather) {
        //db save
        CurrentWeather saveWeather = mongoTemplate.save(currentWeather);
        log.info("Saved: {}", currentWeather);
    }

    public CurrentWeather findCurrentWeather(String cityName) {
        Query query = Query.query(
                Criteria.where("_id").is(cityName)
        );

        CurrentWeather currentWeather = mongoTemplate.findOne(query, CurrentWeather.class);
        log.info("Found CurrentWeather !! cityName : {}", cityName);
        return currentWeather;
    }
}
