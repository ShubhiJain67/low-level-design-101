package behavioral.chainofresponsibility.sample.supporthandlers;

import behavioral.chainofresponsibility.sample.ISupportHandler;

public class SeniorEngineer implements ISupportHandler {
    @Override
    public void setNext(ISupportHandler next){
    }

    @Override
    public void handle(String issue, int difficultyLevel){
        System.out.println("Senior Engineer handeled " + issue + " of difficulty level : " + difficultyLevel + "\n");
    }
}
