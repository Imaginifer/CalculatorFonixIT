/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculator.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author imaginifer
 */
@Service
public class CalculatorService {
    
    private TreeMap<Integer, Double> operandi;
    private List<Operation> operations;

    public CalculatorService() {
        operandi = new TreeMap<>();
        operations = new ArrayList<>();
    }
    
    public String calculate(String rawInput){
        operandi.clear();
        operations.clear();
        
        try {
            identifyOperations(rawInput);
        
            return executeOperations();
        } catch (Exception e){
            return "Input error!";
        }
    }
    
    //identifies the operands and operators in the input string
    private void identifyOperations(String raw) throws Exception{
        String[] splitRaw = raw.split(" ");
        
        for (int i = 0; i < splitRaw.length; i++) {
            String s = splitRaw[i].trim();
            try {
                double d = Double.parseDouble(s);
                if(!operandi.containsKey(i-1)){
                   operandi.put(i, d);
                } else {
                   throw new Exception();
                }
            } catch (NumberFormatException e) {
                if(Operator.OPERATORS.containsKey(s) && operandi.containsKey(i-1) && i < splitRaw.length-1){
                    operations.add(new Operation(i-1, i+1, Operator.OPERATORS.get(s)));
                } else {
                    throw new NumberFormatException();
                }
            }
        }
        if(operations.isEmpty()){
            throw new Exception();
        }
        identifyIllegalOperations();
    }
    
    //identifies whether there is a division by zero, because Java just returns with infinity 
    private void identifyIllegalOperations(){
        operations.forEach((Operation op) -> {
            if(op.getOperator() == Operator.DIVIDE && 
                    operandi.get(op.getRightOperandIdent()) == 0.0){
                throw new ArithmeticException();
            }        
        });
    }
    
    //sorts operations based on precedence, high comes first
    private void setOperationsPrecedence(){
        Set<Operator> low = new HashSet<>(Arrays.asList(new Operator[]{Operator.ADD, Operator.SUBTRACT}));
        
        Collections.sort(operations, ((Operation o1, Operation o2) -> {
            
            return (low.contains(o1.getOperator()) && low.contains(o2.getOperator())) || 
                    (!low.contains(o1.getOperator()) && !low.contains(o2.getOperator())) ? 0 : 
                    low.contains(o1.getOperator()) && !low.contains(o2.getOperator()) ? 1 : -1;
            
        }));
    }
    
    //executes operations in order,insets new operands as results, then return the last 
    private String executeOperations(){
        setOperationsPrecedence();
        
        for (int i = 0; i < operations.size(); i++) {
            Operation op = operations.get(i);
            
            int newKey = operandi.lastKey() + 1;
            double newOperand = 0;
            switch(op.getOperator()){
                case ADD:
                    newOperand = operandi.get(op.getLeftOperandIdent()) + 
                            operandi.get(op.getRightOperandIdent());
                    break;
                case SUBTRACT:
                    newOperand = operandi.get(op.getLeftOperandIdent()) - 
                            operandi.get(op.getRightOperandIdent());
                    break;
                case MULTIPLY:
                    newOperand = operandi.get(op.getLeftOperandIdent()) * 
                            operandi.get(op.getRightOperandIdent());
                    break;
                case DIVIDE:
                    newOperand = operandi.get(op.getLeftOperandIdent()) / 
                            operandi.get(op.getRightOperandIdent());
                    break;
            }
            
            operandi.put(newKey, newOperand);
            
            //update scheduled operations with the results
            for(int j = i + 1; j < operations.size(); j++){
                operations.get(j).updateOperation(op.getLeftOperandIdent(), 
                    op.getRightOperandIdent(), newKey);
            }
        }
        
        return formatResponseNumber(operandi.get(operandi.lastKey()));
    }
    
    //returns three decimal formatted response
    private String formatResponseNumber(double nr){
        
        NumberFormat nf = new DecimalFormat("0.000");
        return nf.format(nr);
    }
    
    
    
    //turns the input string into integer and calls the Fibonacci number calculator
    public String calculateFibonacciNr(String input){
        String response = "Input error!";
        try{
            int nr = Integer.parseInt(input.trim());
            if(nr == 0 || nr == 1){
                return input;
            } else if (nr < 0) {
                return response;
            }
            response = String.valueOf(getFibonacciNr(nr));
        }catch(Exception e){            
        }
        return response;
    }
    
    //the Fibonacci number calculator
    private int getFibonacciNr(int n){  

        int first = 0;
        int second = 1;
        int number = 0; 
        int q = 1;
        
        while(q < n){
            number = first+second;
            first = second;
            second = number;
            q++;
        }
        
        return number;
    }
}
