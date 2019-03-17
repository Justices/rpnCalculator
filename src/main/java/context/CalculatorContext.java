package context;

import calcutor.Calculator;
import com.sun.tools.javac.util.ServiceLoader;
import common.Operator;
import common.OperatorEnum;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CalculatorContext {
    private ServiceLoader<Calculator> serviceLoader = ServiceLoader.load(Calculator.class);
    private Map<OperatorEnum, Calculator> serviceMap = new HashMap();


    public static CalculatorContext getInstance(){
       return CalculatorContextInner.INSTANCE;
    }

    private static class CalculatorContextInner{
        private static CalculatorContext INSTANCE = new CalculatorContext();
    }

    public CalculatorContext(){
        Iterator<Calculator> serviceIterator = serviceLoader.iterator();

        while (serviceIterator.hasNext()){
            Calculator calculator = serviceIterator.next();
            Operator operator = calculator.getClass().getAnnotation(Operator.class);
            serviceMap.put(operator.value(), calculator);
        }
    }

    public Calculator getCalc(String operator){
        OperatorEnum operatorEnum = OperatorEnum.parse(operator);
        return serviceMap.get(operatorEnum);
    }

}
