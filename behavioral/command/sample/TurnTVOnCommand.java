package behavioral.command.sample;

public class TurnTVOnCommand implements ICommand {

    private TV tv;

    public TurnTVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}