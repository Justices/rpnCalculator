package utils;

import exception.CalculatorException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static boolean isNumeric(String input){
        Pattern numPattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher matcher = numPattern.matcher(input);
        return matcher.matches();
    }

    public static String formatInput(String input){
        BigDecimal original = new BigDecimal(input);
        Integer integerPart = original.intValue();
        if(original.compareTo(BigDecimal.valueOf(integerPart))>0){
             return String.valueOf(original.setScale(10, BigDecimal.ROUND_DOWN).doubleValue());
        }
        return String.valueOf(integerPart);
    }

    public static void parseException(Exception exception){
        if(exception instanceof UndeclaredThrowableException){
            UndeclaredThrowableException targetException = (UndeclaredThrowableException)exception;
            if(targetException.getUndeclaredThrowable() instanceof InvocationTargetException){
                InvocationTargetException invokeException = (InvocationTargetException)targetException.getUndeclaredThrowable();
                Throwable rootCause = invokeException.getTargetException();
                if(rootCause instanceof CalculatorException){
                    System.out.println(rootCause.getMessage());
                    return ;
                }
            }
        }
        System.out.println(exception.getMessage());
    }
}
