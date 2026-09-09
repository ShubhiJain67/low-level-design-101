package behavioral.chainofresponsibility.sample.SupportHandlers;

import behavioral.chainofresponsibility.sample.ISupportHandler;

public class L2SupportEngineer implements ISupportHandler {
    private ISupportHandler next;

    @Override
    public void setNext(ISupportHandler next){
        this.next = new JuniorEngineer();
    }

    @Override
    public void handle(String issue){
        if (issue.equals("l2can")) {
            System.out.println("L2 Support handled the issue");
        } else if (this.next != null) {
            this.next.handle(issue);
        }
    }
}
