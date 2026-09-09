package behavioral.template.sample;

import behavioral.template.sample.processors.*;

public class Main {

    public static void main(String[] args) {
        DataProcessor csv = new CSVProcessor();
        csv.process();
        System.out.println();
        DataProcessor json = new JSONProcessor();
        json.process();
    }
}