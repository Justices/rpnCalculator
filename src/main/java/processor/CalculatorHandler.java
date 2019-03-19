package processor;

import calcutor.Calculator;
import common.ResultStack;
import context.CalculatorContext;
import domain.InputElement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2019/3/19.
 */
public class CalculatorHandler implements InvocationHandler {

    private CalculatorContext context;
    private Calculator target;

    public CalculatorHandler(){
        context = CalculatorContext.getInstance();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ResultStack stack = (ResultStack) args[0];
        InputElement inputElement = (InputElement) args[1];
        stack.backup(inputElement);
        method.invoke(target, args);
        return null;
    }

    public Calculator getProxy(InputElement inputElement){
        target = context.getCalc(inputElement.getInput());
        return (Calculator) Proxy.newProxyInstance( target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }
}
