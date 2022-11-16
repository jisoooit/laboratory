package com.example.practice01.junitTest.controller;

import com.example.practice01.junitTest.component.Calculator;
import com.example.practice01.junitTest.dto.CalculatorReq;
import com.example.practice01.junitTest.dto.CalculatorRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/calculator")
public class CalculatorApiController {

    private Calculator calculator;

    @Autowired
    public CalculatorApiController(Calculator calculator) {
        this.calculator = calculator;
    }



    @GetMapping("/sum")
    public int sum(@RequestParam int x,  @RequestParam int y){
        return  calculator.sum(x,y);
    }

    @PostMapping("/minus")
    public CalculatorRes minus(@RequestBody CalculatorReq calculatorReq){
        CalculatorRes calculatorRes = new CalculatorRes();
        int sum = calculator.minus(calculatorReq.getX(), calculatorReq.getY());
        calculatorRes.setResult(sum);
        calculatorRes.setResponse(new CalculatorRes.Body());

        return calculatorRes;

    }
}
