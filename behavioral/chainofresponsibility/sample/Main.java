package behavioral.chainofresponsibility.sample;

import behavioral.chainofresponsibility.sample.SupportHandlers.JuniorEngineer;
import behavioral.chainofresponsibility.sample.SupportHandlers.L1SupportEngineer;
import behavioral.chainofresponsibility.sample.SupportHandlers.L2SupportEngineer;
import behavioral.chainofresponsibility.sample.SupportHandlers.SeniorEngineer;

public class Main {
    public static void main(String[] args) {

        ISupportHandler l1 = new L1SupportEngineer();
        ISupportHandler l2 = new L2SupportEngineer();
        ISupportHandler junior = new JuniorEngineer();
        ISupportHandler senior = new SeniorEngineer();

        l1.setNext(l2);
        l2.setNext(junior);
        junior.setNext(senior);

        l1.handle("l2can");
    }
}
