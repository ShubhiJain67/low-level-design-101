package projects.snakesandladder.models;

import java.util.Random;

public class Dice {
    private final int optionLimit;
    private final Random numberGenerator;

    public Dice(int optionLimit) {
        this.optionLimit = optionLimit;
        this.numberGenerator = new Random();
    }

    public int play(){
        return this.numberGenerator.nextInt(this.optionLimit) + 1;
    }
}
