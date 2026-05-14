package exceptions;

public class CacheFullException extends RuntimeException {

    public CacheFullException(String message) {
        super(message);
    }
}