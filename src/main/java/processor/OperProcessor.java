package processor;

import calcutor.Calculator;
import common.ElementType;
import common.ErrorCode;
import common.ResultStack;
import context.CalculatorContext;
import domain.InputElement;
import domain.PreStep;
import exception.CalculatorException;
import utils.CommonUtils;

import java.util.Stack;

public class OperProcessor {
    private ResultStack stack;
    private CalculatorHandler calculatorHandler;

    public OperProcessor(){
        this.stack = new ResultStack();
        calculatorHandler = new CalculatorHandler();
    }

    public String process(String inputLine){
        String[] strArr = inputLine.split(" ");
        for(int i=0; i< strArr.length; i++){
            int pos = i*2>0?(i*2)+1:1;
            String input = strArr[i];
            InputElement inputElement = new InputElement(input,pos);
            ElementType elementType = inputElement.getElementType();
            boolean isSuccess = elementType.processElement(this,inputElement);
            if(isSuccess == false){
                break;
            }
        }
        return stack.outputString();
    }

    public boolean calculator(InputElement element){
        try{
            Calculator calculator = calculatorHandler.getProxy(element);
            calculator.calculator(stack, element);
            return true;
        }catch (Exception exception ){
            stack.insucientErrorHandler();
            CommonUtils.parseException(exception);
        }
        return false;
    }

    public boolean invalidInput(InputElement inputElement){
        System.out.println("invalid input :" + inputElement.getInput());
        return false;
    }
}
