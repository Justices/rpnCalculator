package processor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OperProcessorTest {
    private OperProcessor operProcessor;
    @Before
    public void init(){
        operProcessor = new OperProcessor();
    }

    @Test
    public void test1() {
        assertTrue("5 2".equals(operProcessor.process("5 2")));
    }

    @Test
    public void test2(){
        assertTrue("1.4142135623".equals(operProcessor.process("2 sqrt")));
        assertTrue("3".equals(operProcessor.process("clear 9 sqrt")));
    }

    @Test
    public void test3() {
        assertTrue("3".equals(operProcessor.process("5 2 -")));
        assertTrue("0".equals(operProcessor.process("3 -")));
        assertTrue("".equals(operProcessor.process("clear")));
    }

    @Test
    public void test4() {
        assertTrue("5 4 3 2".equals(operProcessor.process("5 4 3 2")));
        assertTrue("20".equals(operProcessor.process("undo undo *")));
        assertTrue("100".equals(operProcessor.process("5 *")));
        assertTrue("20 5".equals(operProcessor.process("undo")));
    }


    @Test
    public void test5() {
        assertTrue("7 6".equals(operProcessor.process("7 12 2 /")));
        assertTrue("42".equals(operProcessor.process("*")));
        assertTrue("10.5".equals(operProcessor.process("4 /")));
    }

    @Test
    public void test6() {
        assertTrue("1 2 3 4 5".equals(operProcessor.process("1 2 3 4 5")));
        assertTrue("1 2 3 20".equals(operProcessor.process("*")));
        assertTrue("-1".equals(operProcessor.process("clear 3 4 -")));
    }


    @Test
    public void test8() {
        assertTrue("11".equals(operProcessor.process("1 2 3 * 5 + * * 6 5")));
    }


    @Test
    public void test7() {
        assertTrue("1 2 3 4 5".equals(operProcessor.process("12 3* 5+ * * 6 5")));
        assertTrue("120".equals(operProcessor.process("* * * *")));
    }




    @After
    public void reset(){
        operProcessor.process("clear");
    }


}