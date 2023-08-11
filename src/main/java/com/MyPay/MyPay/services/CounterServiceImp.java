package com.MyPay.MyPay.services;

import com.MyPay.MyPay.entity.Counter;
import com.MyPay.MyPay.entity.Shop;
import com.MyPay.MyPay.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CounterServiceImp implements CounterService {

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private ShopService shopService;


    public CounterServiceImp(CounterRepository counterRepository){

        this.counterRepository =counterRepository;


    }

    @Override
    public List<Counter> findAllCounters(){

        List<Counter> counters = counterRepository.findAll();
        return counters;

    }

    @Override
    public Counter findCounterById(Integer counterid){

        return counterRepository.getCounterById(counterid);

    }


    @Override
    public Optional<Counter> findCountersByShopId(Integer shopId){

        Optional<Counter> counters = counterRepository.getCounterByShopId(shopId);
        return counters;

    }

    @Override
    public ResponseEntity<?> registerCounter(Counter counter, int id){

        Counter newCounter = new Counter();
        newCounter.setWallet(counter.getWallet());

        Optional<Shop> s = shopService.getShopById(id);
        if(s.isPresent()){
            newCounter.setShop(s.get());
        }

        counterRepository.save(newCounter);
        return ResponseEntity.ok().body("Counter is registered successfully");

    }


    @Override
    public Optional<Shop> previewShopDetails(Integer counterId){

        Integer shopId = counterRepository.getShopIdByCounterId(counterId);
        Optional<Shop> shop = shopService.getShopById(shopId);

        return shop;

    }

    @Override
    public void updateWalletWithAmount(int counterid, float amount) {

        Counter c = counterRepository.getCounterById(counterid);
        float currentAmount = c.getWallet();

        counterRepository.updateWalletValue(counterid,currentAmount+amount);

    }


}
