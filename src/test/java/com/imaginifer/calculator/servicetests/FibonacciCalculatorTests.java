/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculator.servicetests;

import org.junit.*;
import static org.junit.Assert.*;


/**
 *
 * @author imaginifer
 */
public class FibonacciCalculatorTests extends CalculatorServiceTestAbstract{
    
    
    @Test
    public void testFibonacciNullInput(){
        String result = cs.calculateFibonacciNr(null);
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testFibonacciEmptyInput(){
        String result = cs.calculateFibonacciNr("");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testFibonacciParsingError(){
        String result = cs.calculateFibonacciNr("qwertzuiop");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testFibonacciDoubleInput(){
       String result = cs.calculateFibonacciNr("6 96");
       assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testFibonacciNegativeInput(){
       String result = cs.calculateFibonacciNr("-6");
       assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testFibonacciZeroInput(){
        String result = cs.calculateFibonacciNr("0");
        assertTrue(result.equals("0"));
    }
    
    @Test
    public void testFibonacciFirstNumber(){
        String result = cs.calculateFibonacciNr("1");
        assertTrue(result.equals("1"));
    }
    
    @Test
    public void testFibonacciSecondNumber(){
        String result = cs.calculateFibonacciNr("2");
        assertTrue(result.equals("1"));
    }
    
    @Test
    public void testFibonacciThirdNumber(){
        String result = cs.calculateFibonacciNr("3");
        assertTrue(result.equals("2"));
    }
    
    @Test
    public void testFibonacciFifthNumber(){
        String result = cs.calculateFibonacciNr("5");
        assertTrue(result.equals("5"));
    }
    
    @Test
    public void testFibonacciEighthNumber(){
        String result = cs.calculateFibonacciNr("8");
        assertTrue(result.equals("21"));
    }
    
    
}
