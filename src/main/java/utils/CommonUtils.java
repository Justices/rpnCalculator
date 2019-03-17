package utils;

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
}
