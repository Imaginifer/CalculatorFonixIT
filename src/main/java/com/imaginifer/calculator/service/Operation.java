/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imaginifer.calculator.service;

/**
 *
 * @author imaginifer
 */
public class Operation {
    private int leftOperandIdent, rightOperandIdent;
    private final Operator operator;

    public Operation(int leftOperandIdent, int rightOperandIdent, Operator operator) {
        this.leftOperandIdent = leftOperandIdent;
        this.rightOperandIdent = rightOperandIdent;
        this.operator = operator;
    }

    public int getLeftOperandIdent() {
        return leftOperandIdent;
    }

    public int getRightOperandIdent() {
        return rightOperandIdent;
    }

    public Operator getOperator() {
        return operator;
    }
    
    public void updateOperation(int leftOpId, int rightOpId, int resultId){
        if (leftOpId == leftOperandIdent || rightOpId == leftOperandIdent){
            leftOperandIdent = resultId;
        } else if (leftOpId == rightOperandIdent || rightOpId == rightOperandIdent){
            rightOperandIdent = resultId;
        }
    }
    
}
