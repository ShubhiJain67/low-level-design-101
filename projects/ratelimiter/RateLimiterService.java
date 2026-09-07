package projects.ratelimiter;

import java.util.Map;

import projects.ratelimiter.enums.UserTierType;

public class RateLimiterService {
    private final RateLimiterConfig defaultConfig;
    private final Map<UserTierType, RateLimiterConfig> configMap;
    private final Map<UserTierType, RateLimiter> limiterMap;

    public RateLimiterService(Map<UserTierType, RateLimiterConfig> configMap, RateLimiterConfig defaultConfig, Map<UserTierType, RateLimiter> limiterMap) {
        this.configMap = configMap;
        this.defaultConfig = defaultConfig;
        this.limiterMap = limiterMap;
    }

    public boolean shouldAllowRequest(User user) {
        UserTierType tier = user.getTier();
        RateLimiterConfig config = this.defaultConfig;
        if (configMap.get(tier) != null) {
            config = configMap.get(tier);
        }
        RateLimiter limiter = this.limiterMap.get(tier);
        return limiter.allowRequest(user.getId(), config);
    }
}
