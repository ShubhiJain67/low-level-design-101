package behavioral.template.sample.processors;

public abstract class DataProcessor {

    public final void process() {
        readData();
        parseData();
        if(shouldValidate()) {
            validateData();
        }
        else {
            System.out.println("Skipping validation");
        }
        saveData();
    }

    void readData() {
        System.out.println("Reading data");
    }

    abstract void parseData();

    // Can override if required
    boolean shouldValidate() {
        return true;
    }

    void validateData() {
        System.out.println("Validating data");
    }

    void saveData() {
        System.out.println("Saving data");
    }
}