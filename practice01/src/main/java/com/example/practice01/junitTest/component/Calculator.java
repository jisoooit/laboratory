package com.example.practice01.junitTest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

    private ICalculator iCalculator;

    @Autowired
    public Calculator(ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }

    public int sum(int x, int y) {
        this.iCalculator.init();
        return this.iCalculator.sum(x,y);
    }

    public int minus(int x, int y){
        this.iCalculator.init();
        return this.iCalculator.minus(x,y);
    }
}
