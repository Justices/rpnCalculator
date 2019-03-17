package exception;

import common.ErrorCode;

public class CalculatorException extends  RuntimeException {
    private ErrorCode errorCode;

    public CalculatorException(String message){
        super(message);
    }

    public CalculatorException(ErrorCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }

}
