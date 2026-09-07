package projects.ratelimiter;

import java.util.EnumMap;
import java.util.Map;

import projects.ratelimiter.enums.*;
import projects.ratelimiter.ratelimiters.ClassicRateLimiter;
import projects.ratelimiter.ratelimiters.SlidingWindowRateLimiter;
import projects.ratelimiter.ratelimiters.TokenBucketRateLimiter;

public class RateLimiterFactory {
    private final Map<RateLimiterType, RateLimiter> instances = new EnumMap<>(RateLimiterType.class);

    public RateLimiter getRateLimiter(RateLimiterType type) {
        if (instances.get(type) == null ) {
            instances.put(type, this.createRateLimiter(type));
        }
        return instances.get(type);
    }

    private RateLimiter createRateLimiter(RateLimiterType type) {
        switch(type) {
            case CLASSIC:
                return new ClassicRateLimiter();
            case SLIDING_WINDOW:
                return new SlidingWindowRateLimiter();
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter();
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
