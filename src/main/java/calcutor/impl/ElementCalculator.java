package calcutor.impl;

import calcutor.Calculator;
import common.Operator;
import common.OperatorEnum;
import common.ResultStack;
import domain.InputElement;

/**
 * Created by Administrator on 2019/3/19.
 */
@Operator(OperatorEnum.ELEMENT)
public class ElementCalculator implements Calculator{
    @Override
    public void calculator(ResultStack stack, InputElement inputElement) {
        stack.pushElement(inputElement);
    }
}
