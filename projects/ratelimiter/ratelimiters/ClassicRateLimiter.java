package projects.ratelimiter.ratelimiters;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import projects.ratelimiter.RateLimiter;
import projects.ratelimiter.RateLimiterConfig;

public class ClassicRateLimiter extends RateLimiter {
    private final Map<String, Integer> requestCounts = new HashMap<>();
    private final Map<String, Instant> windowStartTimes = new HashMap<>();

    @Override
    public boolean allowRequest(String userId, RateLimiterConfig config){
        Instant windowStart = windowStartTimes.get(userId);
        Instant now = Instant.now();

        // First ever request or window of last request is ended
        if (windowStart == null || now.isAfter(windowStart.plus(config.getWindowDuration()))) {
            windowStartTimes.put(userId, now);
            requestCounts.put(userId, 1);
            return true;
        }
        int requestCount = requestCounts.get(userId);
        
        // Rate limited
        if (requestCount >= config.getRequestCount()) {
            return false;
        }

        requestCounts.put(userId, requestCount + 1);

        return true;
    }
}
