package behavioral.chainofresponsibility.sample;

public interface ISupportHandler {
    void setNext(ISupportHandler next, int thresholdDifficulty);
    void handle(String issue, int difficultyLevel);
}
