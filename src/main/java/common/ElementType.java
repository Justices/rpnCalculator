package common;


import domain.InputElement;
import processor.OperProcessor;
import utils.CommonUtils;

public enum ElementType {
    NUMBER(){
        public boolean processElement(OperProcessor operProcessor, InputElement element) {
            return operProcessor.pushElement(element);
        }
    },
    OPERATOR(){
        public boolean processElement(OperProcessor operProcessor, InputElement element) {
            return operProcessor.calculator(element);
        }
    },
    WHITESPACE(){
        public boolean processElement(OperProcessor operProcessor, InputElement element) {
            return true;
        }
    },
    INVALID_INPUT(){
        public boolean processElement(OperProcessor operProcessor, InputElement element) {
            return operProcessor.invalidInput(element);
        }
    };

    public abstract boolean processElement(OperProcessor operProcessor, InputElement element);

    public static ElementType getElementType(String input){
        if(CommonUtils.isNumeric(input)){
            return NUMBER;
        }

        OperatorEnum operatorEnum = OperatorEnum.parse(input);
        if(operatorEnum!=null) {
            return OPERATOR;
        }

        if(Character.isWhitespace(input.charAt(0))){
            return WHITESPACE;
        }

        return INVALID_INPUT;

    }
}
