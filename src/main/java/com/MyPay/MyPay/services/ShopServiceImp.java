package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Merchant;
import com.MyPay.MyPay.entity.Shop;
import com.MyPay.MyPay.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ShopServiceImp implements ShopService {

    private ShopRepository shopRepository;

    @Autowired
    private MerchantService merchantService;
    public ShopServiceImp(ShopRepository shopRepository){

        this.shopRepository = shopRepository;

    }

    @Override
    public Optional<Shop> getShopById(Integer shopId){

        Optional<Shop> shop = Optional.ofNullable(shopRepository.getShopById(shopId));
        return shop;

    }


    @Override
    public List<Shop> findAllShops(){

        List<Shop> shops = shopRepository.findAll();
        return shops;
    }

    @Override
    public Shop findShopByShopName(String shopname){

        return shopRepository.getShopByName(shopname);


    }

    @Override
    public Optional<Shop> findShopsByMerchantId(Integer merchantid){

        Optional<Shop> shops = shopRepository.getShopByMerchantId(merchantid);
        return shops;


    }

    @Override
    public ResponseEntity<?> registerShop(Shop shop, int id){

        Shop newShop = new Shop();
        newShop.setShopname(shop.getShopname());
        newShop.setShopaddress(shop.getShopaddress());

        Optional<Merchant> m = merchantService.findMerchantById(id);
        if(m.isPresent()){
            newShop.setMerchant(m.get());
        }


        shopRepository.save(newShop);
        return ResponseEntity.ok().body("Shop is registered successfully");


    }



}
