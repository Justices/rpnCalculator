package common;

import domain.InputElement;
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
    }

    public void pushElement(InputElement inputElement) {
        String input = inputElement.getInput();
        stack.push(input);
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

   public void backup(InputElement inputElement){
       if(OperatorEnum.isUndo(inputElement.getInput())){
           return ;
       }
       stepProcessor.savepoint((Stack) stack.clone());
   }

   public void undo(InputElement element){
        Stack preStack = stepProcessor.undo();
        if(preStack.isEmpty()){
            System.out.println("operator " + element.getInput() + " (position "+element.getPos()+"):insucient parameter");
        }
        this.stack = preStack;
   }

    public void insucientErrorHandler(){
        Stack<String> lastStack = stepProcessor.undo();
        stack = lastStack;
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
        private List<Stack> stackList = new ArrayList();
        public void savepoint(Stack<String> stack){
            stackList.add(stack);
        }

        public Stack undo(){
            if(stackList.isEmpty()){
                return new Stack<String>();
            }
            Stack stack = stackList.remove(stackList.size()-1);
            return  stack;
        }

   }
}
