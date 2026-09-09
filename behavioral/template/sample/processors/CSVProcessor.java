package behavioral.template.sample.processors;

public class CSVProcessor extends DataProcessor {
    @Override
    void parseData() {
        System.out.println("Parsing CSV data");
    }

    boolean shouldValidate() {
        return false;
    }
}