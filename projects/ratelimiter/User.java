package projects.ratelimiter;

import projects.ratelimiter.enums.UserTierType;

public class User {
    private final String id;
    private UserTierType tier;

    User(String id, UserTierType tier){
        this.id = id;
        this.tier = tier;
    }
    
    public String getId(){
        return this.id;
    }

    public UserTierType getTier(){
        return this.tier;
    }

    public void setTier(UserTierType tier){
        this.tier = tier;
    }
}
