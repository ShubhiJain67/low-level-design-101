package formatter;

import model.LogEntry;

public class JSONFormatter implements ILogFormatter {

    @Override
    public String format(LogEntry logEntry) {
        return """
               {
                 "timestamp": """ + logEntry.getTimestamp() + "\",\n" +
               "  \"level\": \"" + logEntry.getLevel() + "\",\n" +
               "  \"message\": \"" + logEntry.getMessage() + "\",\n" +
               "  \"errorType\": \"" + logEntry.getErrorType() + "\",\n" +
               "  \"metadata\": " + logEntry.getMetadata() + ",\n" +
               "  \"context\": " + logEntry.getContext() + "\n" +
               "}";
    }
}
