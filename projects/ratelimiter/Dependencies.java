package projects.ratelimiter;

import java.util.Map;
import java.time.Duration;

import projects.ratelimiter.enums.RateLimiterType;
import projects.ratelimiter.enums.UserTierType;

public class Dependencies {
    RateLimiterService service;
    
    public Dependencies(){
        RateLimiterFactory factory = new RateLimiterFactory();
        RateLimiterConfig freeTierConfig = new RateLimiterConfig(1, Duration.ofMinutes(1));
        RateLimiterConfig premiumTierConfig = new RateLimiterConfig(10, Duration.ofMinutes(1));
        RateLimiterConfig enterpriseTierConfig = new RateLimiterConfig(100, Duration.ofMinutes(1));

        Map<UserTierType, RateLimiterConfig> configMap = Map.of(
            UserTierType.FREE, freeTierConfig,
            UserTierType.PREMIUM, premiumTierConfig,
            UserTierType.ENTERPRISE, enterpriseTierConfig
        );

        Map<UserTierType, RateLimiter> limiterMap = Map.of(
            UserTierType.FREE, factory.getRateLimiter(RateLimiterType.CLASSIC),
            UserTierType.PREMIUM, factory.getRateLimiter(RateLimiterType.SLIDING_WINDOW),
            UserTierType.ENTERPRISE, factory.getRateLimiter(RateLimiterType.TOKEN_BUCKET)
        );

        this.service = new RateLimiterService(configMap, freeTierConfig, limiterMap);
    }
}
