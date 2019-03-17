package domain;

import common.ElementType;

public class InputElement {
    private String input;
    private ElementType elementType;
    private Integer pos;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public InputElement(String input, Integer pos){
        this.input = input;
        this.elementType = ElementType.getElementType(input);
        this.pos = pos;
    }
}
