package context;


import calcutor.impl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorContextTest {
    private CalculatorContext context;

    @Before
    public void init(){
        context = CalculatorContext.getInstance();
    }

    @Test
    public void getCalc() {
        Assert.assertTrue(context.getCalc("+") instanceof PlusCalculator);
        Assert.assertTrue(context.getCalc("-") instanceof MinusCalculator);
        Assert.assertTrue(context.getCalc("*") instanceof MultiCalculator);
        Assert.assertTrue(context.getCalc("/") instanceof DivisionCalculator);
        Assert.assertTrue(context.getCalc("sqrt") instanceof SqrtCalculator);
        Assert.assertTrue(context.getCalc("undo") instanceof UndoCalculator);
        Assert.assertTrue(context.getCalc("clear") instanceof ClearCalculator);
        Assert.assertTrue(context.getCalc("4.5") instanceof ElementCalculator);
    }
}
