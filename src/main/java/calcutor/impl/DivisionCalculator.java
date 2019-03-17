package calcutor.impl;

import calcutor.Calculator;
import common.ErrorCode;
import common.Operator;
import common.OperatorEnum;
import common.ResultStack;
import domain.InputElement;
import exception.CalculatorException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Operator(OperatorEnum.DIVISION)
public class DivisionCalculator implements Calculator {
    public void calculator(ResultStack stack, InputElement inputElement) {
        BigDecimal first = stack.getElement(inputElement);
        BigDecimal second = stack.getElement(inputElement);
        if(first.compareTo(BigDecimal.ZERO) ==0 ){
            throw new CalculatorException(ErrorCode.INVALID_INPUT, "input parameter error");
        }
        BigDecimal result = second.divide(first).setScale(15, RoundingMode.HALF_UP);
        stack.pushResult(result);
    }
}
