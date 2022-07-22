/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculator.service;

import java.util.*;

/**
 *
 * @author imaginifer
 */
public enum Operator {
    ADD, SUBTRACT, MULTIPLY, DIVIDE;
    
    public static final Map<String, Operator> OPERATORS;
    
    static{
        OPERATORS = new HashMap<>();
        OPERATORS.put("+", ADD);
        OPERATORS.put("-", SUBTRACT);
        OPERATORS.put("*", MULTIPLY);
        OPERATORS.put("/", DIVIDE);
    }
}
