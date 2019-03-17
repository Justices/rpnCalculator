package common;

public enum OperatorEnum {
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
        return null;
    }

    public String getInput(){
        return input;
    }
}
