import processor.OperProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RpnCalculatorApp {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader( System.in ));
        String line;
        OperProcessor processor = new OperProcessor();
        while((line = reader.readLine())!=null){
            String result= processor.process(line);
            System.out.println("stack:" + result );
        }
    }
}
