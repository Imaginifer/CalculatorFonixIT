/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculator.servicetests;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.junit.*;
import static org.junit.Assert.*;


/**
 *
 * @author imaginifer
 */
public class CalculatorTests extends CalculatorServiceTestAbstract{
    
    
    private String formatResponseNumber(double nr){
        
        NumberFormat nf = new DecimalFormat("0.000");
        return nf.format(nr);
    }
    
    private void testGenericCalculation(String input, double output){
        String result = cs.calculate(input);
        assertTrue(result.equals(formatResponseNumber(output)));
    }
    
    @Test
    public void testNullInput(){
        String result = cs.calculate(null);
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testEmptyInput(){
        String result = cs.calculate("");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testLettersInInput(){
        String result = cs.calculate("qwertzuiop");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testNoOperatorsInInput(){
        String result = cs.calculate("13");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testNoSpaceInInput(){
        String result = cs.calculate("13+6");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testSimpleAddition(){
        testGenericCalculation("2 + 2", 4.0);
    }
    
    @Test
    public void testSimpleSubtraction(){
        testGenericCalculation("5 - 2", 3.0);
    }
    
    @Test
    public void testSimpleMultiplication(){
        testGenericCalculation("2 * 2", 4.0);
    }
    
    @Test
    public void testSimpleDivision(){
        testGenericCalculation("4 / 2", 2.0);
    }
    
    @Test
    public void testMissingOperator(){
        String result = cs.calculate("5 2");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testDecimalParsing(){
        testGenericCalculation("13.3 - 11.3", 2.0);
    }
    
    @Test
    public void testNegativeOperand(){
        testGenericCalculation("5 - -2", 7.0);
    }
    
    @Test
    public void testDivisionByZero(){
        String result = cs.calculate("4 / 0");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testMissingRightOperand(){
        String result = cs.calculate("5 /");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testFractionResult(){
        testGenericCalculation("5 / 2", 2.5);
    }
    
    @Test
    public void testNegativeResult(){
        testGenericCalculation("1 - 546", -545.0);
    }
    
    @Test
    public void testPositiveResult(){
        testGenericCalculation("-1 * -5", 5.0);
    }
    
    @Test
    public void testComplexAddition(){
        testGenericCalculation("6 + 4 + 9 + 8.2", 27.2);
    }
    
    @Test
    public void testComplexAdditionAndSubtraction(){
        testGenericCalculation("6 - 4 + 9 - 8.2", 2.8);
    }
    
    @Test
    public void testComplexMultiplicationAndDivision(){
        testGenericCalculation("4 * 15 / 6 * 2", 20.0);
    }
    
    @Test
    public void testOperatorPrecedence(){
        testGenericCalculation("8 + 6 * 0", 8.0);
    }
    
    @Test
    public void testBracketInput(){
        String result = cs.calculate("( 13 + 6 ) * 2");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testDoubledOperator(){
        String result = cs.calculate("13 + - 6 * 2");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testSelectivelyMissingOperator(){
        String result = cs.calculate("5 13 + 6 2");
        assertTrue(result.equals("Input error!"));
    }
    
    @Test
    public void testExcessWhitespace(){
        String result = cs.calculate("5  +    2");
        assertTrue(result.equals("Input error!"));
    }
    
}
