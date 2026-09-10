package behavioral.chainofresponsibility.sample;

public interface ISupportHandler {
    void setNext(ISupportHandler next);
    void handle(String issue, int difficultyLevel);
}
