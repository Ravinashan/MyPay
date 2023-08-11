package com.MyPay.MyPay.controller;

import com.MyPay.MyPay.entity.Counter;
import com.MyPay.MyPay.entity.Shop;
import com.MyPay.MyPay.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080/")
public class CounterController {

    @Autowired
    private CounterService counterService;

    @GetMapping(value = "/counters")
    public List<Counter> findAllCounters(){

        return counterService.findAllCounters();

    }

    @GetMapping(value = "/counters/{id}")
    public Optional<Counter> findCountersByShopId(@PathVariable Integer id){

        return counterService.findCountersByShopId(id);


    }

    @PostMapping(value = "/{id}/registercounter")
    public ResponseEntity<?> registerCounter(@PathVariable int id, @RequestBody Counter counter){

        return counterService.registerCounter(counter, id);
    }

    @GetMapping(value = "/previewShop/{id}")
    public Optional<Shop> previewShopDetails(@PathVariable Integer id){

        return counterService.previewShopDetails(id);

    }







}

