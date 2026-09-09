package behavioral.command.sample;

public class TurnTVOffCommand implements ICommand {

    private TV tv;

    public TurnTVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}