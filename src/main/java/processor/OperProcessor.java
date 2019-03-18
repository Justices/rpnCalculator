package processor;

import calcutor.Calculator;
import common.ElementType;
import common.ErrorCode;
import common.ResultStack;
import context.CalculatorContext;
import domain.InputElement;
import exception.CalculatorException;

public class OperProcessor {
    private ResultStack stack;
    private CalculatorContext context;

    public OperProcessor(){
        this.stack = new ResultStack();
        context = CalculatorContext.getInstance();
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

    public boolean pushElement(InputElement element){
        stack.pushElement(element);
        return true;
    }

    public boolean calculator(InputElement element){
        try{
            String input = element.getInput();
            Calculator calculator = context.getCalc(input);
            calculator.calculator(stack, element);
            return true;
        }catch (CalculatorException exception){
            ErrorCode errorCode = exception.getErrorCode();
            if(errorCode.compareTo(ErrorCode.OPERATOR_NOT_SUPPORT)==0){
                System.out.println(exception.getMessage());
                stack.insucientErrorHandler();
                return false;
            }

        }
        return false;
    }

    public boolean invalidInput(InputElement inputElement){
        System.out.println("invalid input :" + inputElement.getInput());
        return false;
    }
}
