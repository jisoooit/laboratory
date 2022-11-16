package com.example.practice01.junitTest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DollarCalculator implements  ICalculator{

    int price = 1;


    private MarketApi marketApi;

    @Autowired
    public DollarCalculator(MarketApi marketApi) {
        this.marketApi = marketApi;
    }

    @Override
    public void init(){
        this.price = marketApi.connect();
    }

    @Override
    public int sum(int x, int y) {
        x *=price;
        y*=price;
        return x+y;
    }

    @Override
    public int minus(int x, int y) {
        x *=price;
        y*=price;
        return x-y;
    }
}
