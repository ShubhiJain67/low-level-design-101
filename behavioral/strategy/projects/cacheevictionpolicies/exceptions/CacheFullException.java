package behavioral.strategy.projects.cacheevictionpolicies.exceptions;

public class CacheFullException extends RuntimeException {

    public CacheFullException(String message) {
        super(message);
    }
}