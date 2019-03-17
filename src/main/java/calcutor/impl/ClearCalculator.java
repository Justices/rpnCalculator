package calcutor.impl;

import calcutor.Calculator;
import common.Operator;
import common.OperatorEnum;
import common.ResultStack;
import domain.InputElement;

@Operator(OperatorEnum.CLEAR)
public class ClearCalculator implements Calculator {
    public void calculator(ResultStack stack, InputElement inputElement) {
        stack.clear();
    }
}
