package projects.ratelimiter.ratelimiters;
import java.time.*;
import java.util.*;

import projects.ratelimiter.RateLimiter;
import projects.ratelimiter.RateLimiterConfig;

public class TokenBucketRateLimiter extends RateLimiter {
    private final Map<String, TokenBucketState> buckets = new HashMap<>();

    @Override
    public boolean allowRequest(String userId, RateLimiterConfig config){
        Instant now = Instant.now();

        TokenBucketState currentState = buckets.get(userId);

        if (currentState == null) {
            currentState = new TokenBucketState(config.getRequestCount()-1, now);
            buckets.put(userId, currentState);
            return true;
        }
        
        Duration timeElapsed = Duration.between(currentState.getLastRefillTime(), now);
        double tokensPerMs = (double) config.getRequestCount() / config.getWindowDuration().toMillis();
        double newTokens = timeElapsed.toMillis() * tokensPerMs;
        
        double newAvailableTokens = currentState.getAvailableTokens() + newTokens;
        
        if (newAvailableTokens >= 1) {
            currentState.setAvailableTokens(newAvailableTokens - 1);
            currentState.setLastRefillTime(now);
            return true;
        }

        return false;
    }
}
