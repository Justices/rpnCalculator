package common;

public enum ErrorCode {
    INVALID_INPUT("0000001", "input not support"),
    OPERATOR_NOT_SUPPORT("0000002", "operator error");
    String code;
    String desc;

    ErrorCode(String  code, String desc){
        this.code = code;
        this.desc = desc;
    }


    @Override
    public String toString(){
        return this.desc;
    }
}
