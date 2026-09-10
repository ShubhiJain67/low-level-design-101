package behavioral.chainofresponsibility.sample.supporthandlers;

import behavioral.chainofresponsibility.sample.ISupportHandler;

public class L2SupportEngineer implements ISupportHandler {
    private ISupportHandler next;
    private int thresholdDifficulty;

    @Override
    public void setNext(ISupportHandler next, int thresholdDifficulty){
        this.next = new JuniorEngineer();
        this.thresholdDifficulty = thresholdDifficulty;
    }

    @Override
    public void handle(String issue, int difficultyLevel){
        if (difficultyLevel == this.thresholdDifficulty) {
            System.out.println("L2 Support handled the issue " + issue + " of difficulty level : " + difficultyLevel);
        } else if (this.next != null) {
            System.out.println("Passing the issue from l2 supoort engineer to next level");
            this.next.handle(issue, difficultyLevel);
        }
    }
}
