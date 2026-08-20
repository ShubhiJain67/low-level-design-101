package creational.builder.sample;

import java.util.Map;

public class LogLineStep {
    private final String message;
    private final String level;
    private final Map<String, String> metadata;

    private LogLineStep(String message, String level, Map<String, String> metadata) {
        this.message = message;
        this.level = level;
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

    public static MessageStep builder() {
        return new Steps();
    }

    // Each interface only exposes the next required step — enforced at compile time.
    public interface MessageStep {
        LevelStep withMessage(String message);
    }

    public interface LevelStep {
        BuildStep withLevel(String level);
    }

    public interface BuildStep {
        BuildStep withMetadata(Map<String, String> metadata);
        LogLineStep build();
    }

    private static class Steps implements MessageStep, LevelStep, BuildStep {
        private String message;
        private String level;
        private Map<String, String> metadata;

        @Override
        public LevelStep withMessage(String message) {
            this.message = message; return this;
        }

        @Override
        public BuildStep withLevel(String level) {
            this.level = level; return this;
        }

        @Override
        public BuildStep withMetadata(Map<String, String> metadata) {
            this.metadata = metadata; return this;
        }

        @Override
        public LogLineStep build() {
            return new LogLineStep(message, level, metadata);
        }
    }
}
