package calcutor.impl;

import calcutor.Calculator;
import common.Operator;
import common.OperatorEnum;
import common.ResultStack;
import domain.InputElement;

import java.math.BigDecimal;

@Operator(OperatorEnum.SQRT)
public class SqrtCalculator implements Calculator {
    public void calculator(ResultStack stack, InputElement inputElement) {
        BigDecimal first = stack.getElement(inputElement);
        BigDecimal result = BigDecimal.valueOf(Math.sqrt(first.doubleValue()));
        stack.pushResult(result);
    }
}
