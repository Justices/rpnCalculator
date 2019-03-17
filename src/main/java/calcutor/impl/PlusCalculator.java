package calcutor.impl;

import calcutor.Calculator;
import common.Operator;
import common.OperatorEnum;
import common.ResultStack;
import domain.InputElement;

import java.math.BigDecimal;

@Operator(OperatorEnum.PLUS)
public class PlusCalculator implements Calculator {

    public void calculator(ResultStack stack, InputElement inputElement) {
        BigDecimal first = stack.getElement(inputElement);
        BigDecimal second = stack.getElement(inputElement);
        BigDecimal result = first.add(second);
        stack.pushResult(result);
    }
}
