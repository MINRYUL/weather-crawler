package org.ajou.realcoding.weathercrawler.service;

import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.ajou.realcoding.weathercrawler.repository.DogManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogsManagementService {

    @Autowired
    private DogManagementRepository dogsManagementrepository;

    public void createDog(Dog dog) {
        dogsManagementrepository.insertDog(dog);
    }

    public Dog findDog(String name) {
        return dogsManagementrepository.findDog(name);
    }
}
