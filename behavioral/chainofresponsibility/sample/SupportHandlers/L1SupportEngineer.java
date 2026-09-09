package behavioral.chainofresponsibility.sample.SupportHandlers;

import behavioral.chainofresponsibility.sample.ISupportHandler;

public class L1SupportEngineer implements ISupportHandler {
    private ISupportHandler next;

    @Override
    public void setNext(ISupportHandler next){
        this.next = new L2SupportEngineer();
    }

    @Override
    public void handle(String issue){
        if (issue.equals("l1can")) {
            System.out.println("L1 Support handled the issue");
        } else if (this.next != null) {
            this.next.handle(issue);
        }
    }
}
