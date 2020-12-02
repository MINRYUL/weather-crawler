package org.ajou.realcoding.weathercrawler.repository;

import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DogManagementRepository {
    List<Dog> dogList = new ArrayList<>();

    public void insertDog(Dog dog){
        dogList.add(dog);
    }

    public Dog findDog(String name) {
        for(Dog dog : dogList){
            if(dog.getName().equals(name)){
                return dog;
            }
        }

        throw new RuntimeException();
    }
}
