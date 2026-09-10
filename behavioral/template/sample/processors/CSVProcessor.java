package behavioral.template.sample.processors;

public class CSVProcessor extends DataProcessor {
    @Override
    void parseData() {
        System.out.println("Parsing CSV data");
    }

    @Override
    boolean shouldValidate() {
        return false;
    }
}