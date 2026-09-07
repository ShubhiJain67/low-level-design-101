package projects.ratelimiter.ratelimiters;

import java.time.Instant;

public class TokenBucketState {
    private double availableTokens;
    private Instant lastRefillTime;

    public TokenBucketState(double availableTokens, Instant lastRefillTime) {
        this.availableTokens = availableTokens;
        this.lastRefillTime = lastRefillTime;
    }

    public double getAvailableTokens() {
        return availableTokens;
    }

    public void setAvailableTokens(double availableTokens) {
        this.availableTokens = availableTokens;
    }

    public Instant getLastRefillTime() {
        return lastRefillTime;
    }

    public void setLastRefillTime(Instant lastRefillTime) {
        this.lastRefillTime = lastRefillTime;
    }
}
