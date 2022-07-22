/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculatortests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imaginifer.calculator.CalcDTO;
import com.imaginifer.calculator.CalculatorController;
import com.imaginifer.calculator.service.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author imaginifer
 */
public class ControllerTests {
    
    private MockMvc mvc;
    @Mock
    private CalculatorService cs;
    @InjectMocks
    private CalculatorController ctrl;
    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(ctrl).build();
        Mockito.when(cs.calculate(Mockito.anyString())).thenReturn("calculate");
        Mockito.when(cs.calculateFibonacciNr(Mockito.anyString())).thenReturn("fibonacci");
        
    }
    
    private String jsonBuilder(String txt) throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(new CalcDTO(txt));
    }
    
    private RequestBuilder jsonRequestMaker(String url, String txt) throws JsonProcessingException{
        return MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON).content(jsonBuilder(txt))
                .contentType(MediaType.APPLICATION_JSON);
    }
    
    @Test
    public void testControllerInitialization() {
        assertNotNull(ctrl);
    }
    
    @Test
    public void testFibonacciUrl() throws Exception{
        mvc.perform(jsonRequestMaker("/calculator/fibonacci", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void testCalculatorUrl() throws Exception{ 
        mvc.perform(jsonRequestMaker("/calculator/calculate", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void testCalculatorResponse() throws Exception{
        mvc.perform(jsonRequestMaker("/calculator/calculate", "test"))
                .andExpect(MockMvcResultMatchers.content().json(jsonBuilder("calculate")));
    }
    
    @Test
    public void testFibonacciResponse() throws Exception{
        mvc.perform(jsonRequestMaker("/calculator/fibonacci", "test"))
                .andExpect(MockMvcResultMatchers.content().json(jsonBuilder("fibonacci")));
    }
}
