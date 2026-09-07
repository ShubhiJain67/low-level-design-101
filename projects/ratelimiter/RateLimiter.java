package projects.ratelimiter;

public abstract class RateLimiter {
    public abstract boolean allowRequest(String userId, RateLimiterConfig config);
}
