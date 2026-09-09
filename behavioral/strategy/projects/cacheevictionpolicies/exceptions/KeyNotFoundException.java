package behavioral.strategy.projects.cacheevictionpolicies.exceptions;

public class KeyNotFoundException
        extends RuntimeException {

    public KeyNotFoundException(String message) {
        super(message);
    }
}