package formatter;

import model.LogEntry;

public class TextFormatter implements ILogFormatter {

    @Override
    public String format(LogEntry logEntry) {
        return "Timestamp: " + logEntry.getTimestamp() + "\n" +
               "Level: " + logEntry.getLevel() + "\n" +
               "Message: " + logEntry.getMessage() + "\n" +
               "Error Type: " + logEntry.getErrorType() + "\n" +
               "Metadata: " + logEntry.getMetadata() + "\n" +
               "Context: " + logEntry.getContext(); 
    }
}
