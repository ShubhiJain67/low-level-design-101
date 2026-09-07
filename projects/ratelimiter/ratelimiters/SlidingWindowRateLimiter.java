package projects.ratelimiter.ratelimiters;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import projects.ratelimiter.RateLimiter;
import projects.ratelimiter.RateLimiterConfig;

public class SlidingWindowRateLimiter extends RateLimiter {
    private final Map<String, Queue<Instant>> requests = new HashMap<>();

    @Override
    public boolean allowRequest(String userId, RateLimiterConfig config){
        Queue<Instant> existingRequests = requests.get(userId);
        Instant now = Instant.now();

        if (existingRequests == null) {
            existingRequests = new LinkedList<>();
        }

        Instant allowedStart = now.minus(config.getWindowDuration());
        while (existingRequests.size() != 0 && existingRequests.peek().isBefore(allowedStart)) {
            existingRequests.poll();
        }

        // First ever request or window of last request is ended
        if (existingRequests.size() == 0) {
            existingRequests.offer(now);
            requests.put(userId, existingRequests);
            return true;
        }
        
        // Rate limited
        if (existingRequests.size() >= config.getRequestCount()) {
            return false;
        }

        existingRequests.offer(now);

        return true;
    }
}
