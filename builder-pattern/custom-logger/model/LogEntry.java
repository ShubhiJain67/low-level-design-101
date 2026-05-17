package model;

import java.util.Map;
import javax.naming.Context;

public class LogEntry {
    private final LogLevel level;
    private final String message;
    private final ErrorType errorType;
    private final String timestamp;
    private final Map<String, String> metadata;
    private final Context context;
    

    public LogEntry(LogLevel logType, String message, ErrorType errorType, String timestamp, Map<String, String> metadata, Context context) {
        this.level = logType;
        this.message = message;
        this.errorType = errorType;
        this.timestamp = timestamp;
        this.metadata = metadata;
        this.context = context;
    }

    public LogLevel getLevel() {
        return level;
    }
    public String getMessage() {
        return message;
    }
    public ErrorType getErrorType() {
        return errorType;  
    }
    public String getTimestamp() {
        return timestamp;
    }
    public Map<String, String> getMetadata() {
        return metadata;
    }
    public Context getContext() {
        return context;
    }
}
