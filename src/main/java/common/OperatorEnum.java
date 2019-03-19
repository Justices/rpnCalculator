package common;

import exception.CalculatorException;
import utils.CommonUtils;

public enum OperatorEnum {
    ELEMENT("number"),
    PLUS("+"),
    MINUS("-"),
    MULTI("*"),
    DIVISION("/"),
    SQRT("sqrt"),
    UNDO("undo"),
    CLEAR("clear");


    OperatorEnum(String input){
        this.input = input;
    }

    String input;

    public static OperatorEnum parse(String input){
        for(OperatorEnum operatorEnum: OperatorEnum.values()){
            if(operatorEnum.getInput().compareTo(input)==0){
                return operatorEnum;
            }
        }

        if(CommonUtils.isNumeric(input)){
            return ELEMENT;
        }
        return null;
    }

    public String getInput(){
        return input;
    }

    public static boolean isUndo(String input){
        OperatorEnum  operatorEnum =  OperatorEnum.parse(input);
        if(operatorEnum == null && !CommonUtils.isNumeric(input)){
            throw new CalculatorException(ErrorCode.OPERATOR_NOT_SUPPORT, "wrong operation");
        }
        if(UNDO.compareTo(operatorEnum)==0){
            return true;
        }
        return false;
    }

}
