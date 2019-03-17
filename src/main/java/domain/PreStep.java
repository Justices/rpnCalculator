package domain;

import java.util.Stack;

public class PreStep {

    public PreStep(boolean isResult, Stack<String> stack){
        this.isResult = isResult;
        this.preStack = stack;
    }
    public boolean isResult() {
        return isResult;
    }

    public void setResult(boolean result) {
        isResult = result;
    }

    private boolean isResult;
    private Stack<String> preStack;

    public Stack<String> getPreStack() {
        return preStack;
    }

    public void setPreStack(Stack<String> preStack) {
        this.preStack = preStack;
    }
}
