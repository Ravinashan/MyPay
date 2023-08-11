package com.MyPay.MyPay.controller;

import com.MyPay.MyPay.entity.Merchant;
import com.MyPay.MyPay.entity.Shop;
import com.MyPay.MyPay.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:8080/")
public class ShopController {


    @Autowired
    private ShopService shopService;

    @GetMapping(value = "/shops")
    public List<Shop> findAllShops(){

        return shopService.findAllShops();
    }

    @GetMapping(value = "/shops/{id}")
    public Optional<Shop> findShopById(@PathVariable Integer id){

        return shopService.getShopById(id);
    }



    @PostMapping(value = "/{id}/registershop")
    public ResponseEntity<?> registerShop(@PathVariable int id, @RequestBody Shop shop){

        return shopService.registerShop(shop, id);
    }

}
