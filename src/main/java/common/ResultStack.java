package common;

import domain.InputElement;
import domain.PreStep;
import exception.CalculatorException;
import utils.CommonUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ResultStack {
    private StepProcessor stepProcessor;

    public ResultStack(){
        stepProcessor = new StepProcessor();
    }

    private Stack<String> stack = new Stack();


    public void pushResult(BigDecimal element){
        String input = element.setScale(15, RoundingMode.HALF_UP).toPlainString();
        stack.push(input);
        PreStep preStep = new PreStep(true, (Stack) stack.clone());
        stepProcessor.savepoint(preStep);
    }

    public void pushElement(InputElement inputElement) {
        String input = inputElement.getInput();
        stack.push(input);
        PreStep preStep = new PreStep(false, (Stack) stack.clone());
        stepProcessor.savepoint(preStep);
    }

   public BigDecimal getElement(InputElement element){
        if(stack.isEmpty()) {
            throw new CalculatorException(ErrorCode.OPERATOR_NOT_SUPPORT, "operator " + element.getInput() + " (position "+element.getPos()+"):insucient parameter");
        }
        return new BigDecimal(stack.pop());
   }

   public void clear(){
        stack.clear();
   }

   public void undo(InputElement element){
        Stack preStack = stepProcessor.undo();
        if(preStack ==null){
            throw new CalculatorException(ErrorCode.OPERATOR_NOT_SUPPORT, "operator " + element.getInput() + " (position "+element.getPos()+"):insucient parameter");
        }
        this.stack = preStack;
   }

    public void insucientErrorHandler(){
        List<PreStep> stackList = stepProcessor.stackList;
        PreStep lastElement = stackList.get(stackList.size()-1);
        Stack<String>  lastStack = lastElement.getPreStack();
        int size = lastStack.size();
        String resetValue = lastStack.get(size-1);
        stack.push(resetValue);
    }



   public String outputString(){
       StringBuffer stringBuffer = new StringBuffer();
       for(String element:stack){
           stringBuffer.append(CommonUtils.formatInput(element));
           stringBuffer.append(" ");
       }
       String result = stringBuffer.toString().trim();
       return result;
   }

   private class StepProcessor{
        private List<PreStep> stackList = new ArrayList();
        public void savepoint(PreStep preStep){
            stackList.add(preStep);
        }

        public Stack undo(){
            if(stackList.isEmpty()){
                return null;
            }
            PreStep lastElement = stackList.remove(stackList.size()-1);
            Stack<String> stack = lastElement.getPreStack();
            if(lastElement.isResult()){
                lastElement = stackList.remove(stackList.size()-1);
                return lastElement.getPreStack();
            }
            stack.pop();
            return  stack;
        }

   }
}
