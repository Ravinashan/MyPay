package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Shop;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    List<Shop> findAllShops();

    Shop findShopByShopName(String shopname);

    Optional<Shop> getShopById(Integer shopId);

    Optional<Shop> findShopsByMerchantId(Integer merchantid);

    ResponseEntity<?> registerShop(Shop shop, int id);


}
