package builder;

import java.util.HashMap;
import java.util.Map;
import javax.naming.Context;
import model.ErrorType;
import model.LogEntry;
import model.LogLevel;

public class LogBuilder {
    private LogLevel level;
    private String message;
    private ErrorType errorType;
    private String timestamp;
    private Map<String, String> metadata;
    private Context context;

    public LogBuilder(LogLevel level) {
        this.level = level;
        this.metadata = new HashMap<>();
    }

    public LogBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public LogBuilder withErrorType(ErrorType errorType) {
        this.errorType = errorType;
        return this;
    }

    public LogBuilder withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public LogBuilder withMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    public LogBuilder withContext(Context context) {
        this.context = context;
        return this;
    }

    public LogBuilder addMetadata(Map<String, String> metadata) {
        this.metadata.putAll(metadata);
        return this;
    }

    public LogEntry build() {
        return new LogEntry(level, message, errorType, timestamp, metadata, context);
    }

}
