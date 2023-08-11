package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Counter;
import com.MyPay.MyPay.entity.Shop;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CounterService {

    List<Counter> findAllCounters();

    Counter findCounterById(Integer counterid);

    Optional<Counter> findCountersByShopId(Integer shopId);

    ResponseEntity<?> registerCounter(Counter counter, int id);

    Optional<Shop> previewShopDetails(Integer counterId);


    void updateWalletWithAmount(int counterid, float amount);
}
