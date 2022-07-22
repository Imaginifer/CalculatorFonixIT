/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculator.servicetests;

import com.imaginifer.calculator.service.*;
import org.junit.Before;

/**
 *
 * @author imaginifer
 */
abstract class CalculatorServiceTestAbstract {
    
    protected CalculatorService cs;
    
    @Before
    public void setUp(){
        cs = new CalculatorService();
    }
}
