package behavioral.chainofresponsibility.sample.SupportHandlers;

import behavioral.chainofresponsibility.sample.ISupportHandler;

public class SeniorEngineer implements ISupportHandler {
    private ISupportHandler next;

    @Override
    public void setNext(ISupportHandler next){
        this.next = new JuniorEngineer();
    }

    @Override
    public void handle(String issue){
        System.out.println("Senior Engineer handeled " + issue);
    }
}
