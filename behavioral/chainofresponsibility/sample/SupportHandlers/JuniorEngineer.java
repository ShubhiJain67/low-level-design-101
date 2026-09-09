package behavioral.chainofresponsibility.sample.SupportHandlers;

import behavioral.chainofresponsibility.sample.ISupportHandler;

public class JuniorEngineer implements ISupportHandler {
    private ISupportHandler next;

    @Override
    public void setNext(ISupportHandler next){
        this.next = new SeniorEngineer();
    }

    @Override
    public void handle(String issue){
        if (issue.equals("juniorcan")) {
            System.out.println("L1 Support handled the issue");
        } else if (this.next != null) {
            this.next.handle(issue);
        }
    }
}
