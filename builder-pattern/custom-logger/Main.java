import java.util.Map;
import logger.Logger;
import logger.LoggerManager;
import model.ErrorType;
import model.LogEntry;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerManager.getInstance().getLogger();

        try {

            int result = 10 / 0;
            System.err.println("Result: " + result);

        } catch (Exception ex) {

            LogEntry entry = logger
                    .error()
                    .withMessage("Payment processing failed")
                    .withErrorType(ErrorType.VALIDATION_ERROR)
                    .addMetadata(Map.of(
                            "paymentId",
                            "PAY-101"
                    ))
                    .build();

            logger.log(entry);
        }
    }
}