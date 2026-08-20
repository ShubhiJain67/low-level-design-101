package creational.builder.sample;

import java.util.Map;

public class LogLineClassic {
    private String message;
    private String level;
    private Map<String, String> metadata;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public void log() {
        System.out.print("LogLine: " + level + " - " + message);
        if (metadata != null && !metadata.isEmpty()) {
            System.out.println(" Metadata: " + metadata);
        } else {
            System.out.println();
        }
    }

    public static class Builder {
        private final LogLineClassic logLine = new LogLineClassic();

        public void buildMessage(String message) {
            logLine.setMessage(message);
        }

        public void buildLevel(String level) {
            logLine.setLevel(level);
        }

        public void buildMetadata(Map<String, String> metadata) {
            logLine.setMetadata(metadata);
        }

        public LogLineClassic getResult() {
            return logLine;
        }
    }

    // Director — client hands it a builder, never calls buildX() itself.
    public static class Engineer {
        public LogLineClassic construct(Builder builder, String message, String level, Map<String, String> metadata) {
            builder.buildMessage(message);
            builder.buildLevel(level);
            builder.buildMetadata(metadata);
            return builder.getResult();
        }
    }
}
