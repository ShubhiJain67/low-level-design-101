package behavioral.chainofresponsibility.sample.supporthandlers;

import behavioral.chainofresponsibility.sample.ISupportHandler;

public class JuniorEngineer implements ISupportHandler {
    private ISupportHandler next;
    private final int THRESHOLD = 3;

    @Override
    public void setNext(ISupportHandler next){
        this.next = next;
    }

    @Override
    public void handle(String issue, int difficultyLevel){
        if (difficultyLevel <= this.THRESHOLD) {
            System.out.println("L1 Support handled the issue " + issue  + " of difficulty level : " + difficultyLevel + "\n");
        } else if (this.next != null) {
            System.out.println("Passing the issue from junior engineer to next level");
            this.next.handle(issue, difficultyLevel);
        }
    }
}
