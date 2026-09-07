package projects.ratelimiter;

import projects.ratelimiter.enums.*;

public class Main {
    static Dependencies dependencies;

    public static void main(String args[]) {
        dependencies = new Dependencies();
        User shubhi = new User("shubhi", UserTierType.FREE);
        User prateek = new User("prateek", UserTierType.PREMIUM);

        burstCalls(shubhi, 10);
        burstCalls(prateek, 10);
    }

    public static void burstCalls(User user, int count){
        for(int j = 1; j<=count; j++) {
            if (dependencies.service.shouldAllowRequest(user)) {
                System.out.println("Allowed " + user.getId());
            } else {
                System.out.println("Rejected " + user.getId());
            }
            
        }

    }
}
