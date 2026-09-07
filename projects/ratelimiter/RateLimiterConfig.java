package projects.ratelimiter;

import java.time.Duration;

public class RateLimiterConfig {
    private final Duration window;
    private final int requestCount;

    public RateLimiterConfig(int requestCount, Duration window){
        this.window = window;
        this.requestCount = requestCount;
    }

    public Duration getWindowDuration(){
        return this.window;
    }

    public int getRequestCount(){
        return this.requestCount;
    }
}
