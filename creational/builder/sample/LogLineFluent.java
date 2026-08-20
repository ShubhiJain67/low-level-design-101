package creational.builder.sample;

import java.util.Map;

public class LogLineFluent{

    private String message;
    private String level;
    private Map<String, String> metadata;

    private LogLineFluent(String message, String level, Map<String, String> metadata) {
        this.message = message;
        this.level = level;
        this.metadata = metadata;
    }

    public void log() {
        System.out.print("LogLine: " + this.level + " - " + this.message);
        if (metadata != null && !metadata.isEmpty()) {
            System.out.println(" Metadata: " + metadata);
        } else {
            System.out.println();
        }
    }

    // static nested class
    public static class LogBuilderFluent {

        private String message;
        private String level;
        private Map<String, String> metadata;

        public LogBuilderFluent withMessage(String message) {
            this.message = message;
            return this;
        }

        public LogBuilderFluent withLogLevel(String logLevel) {
            this.level = logLevel;
            return this;
        }

        public LogBuilderFluent withMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public LogLineFluent build() {
            if (this.message == null || this.level == null) {
                throw new IllegalStateException(
                    "Message and log level must be provided"
                );
            }
            return new LogLineFluent(
                this.message,
                this.level,
                this.metadata
            );
        }
    }
}