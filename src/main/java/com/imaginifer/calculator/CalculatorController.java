/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculator;

import com.imaginifer.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author imaginifer
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorController {
    
    private final CalculatorService calcSrv;

    @Autowired
    public CalculatorController(CalculatorService cs) {
        this.calcSrv = cs;
    }
    
    
    @PostMapping("/calculate")
    public CalcDTO requestToCalculate(@RequestBody CalcDTO dto){
        return new CalcDTO(calcSrv.calculate(dto.getTxt()));
    }
    
    @PostMapping("/fibonacci")
    public CalcDTO requestToFindFibonacciNr(@RequestBody CalcDTO dto){
        return new CalcDTO(calcSrv.calculateFibonacciNr(dto.getTxt()));
    }
}
