package creational.builder.sample;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Fluent — self-returning withX(), no Director, client chains directly.
        LogLineFluent fluentLine = new LogLineFluent.LogBuilderFluent()
                .withMessage("Payment successful")
                .withLogLevel("INFO")
                .withMetadata(Map.of("paymentId", "PAY-101"))
                .build();
        fluentLine.log();

        // Classic GoF — Director (Engineer) drives a Builder interface.
        LogLineClassic.Engineer engineer = new LogLineClassic.Engineer();
        LogLineClassic classicLine = engineer.construct(
                new LogLineClassic.Builder(),
                "Payment failed",
                "ERROR",
                null
        );
        classicLine.log();

        // Step Builder — required fields enforced at compile time, not runtime.
        LogLineStep stepLine = LogLineStep.builder()
                .withMessage("Payment retried")
                .withLevel("WARN")
                .withMetadata(Map.of("attempt", "2"))
                .build();
        stepLine.log();
    }
}
