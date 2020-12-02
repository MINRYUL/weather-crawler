package org.ajou.realcoding.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.domain.Dog;
import org.ajou.realcoding.weathercrawler.service.DogsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DogsManagementController {

    @Autowired
    private DogsManagementService dogsManagementService;

    @PostMapping("/dogs")
    public void createDog(@RequestBody Dog requestBody){
        dogsManagementService.createDog(requestBody);
    }

    // http://localhost:8080/dogs?name=Ian
    @GetMapping("/dogs")
    public Dog findDog(@RequestParam String name) {
        return dogsManagementService.findDog(name);
    }
}
