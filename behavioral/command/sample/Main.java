package behavioral.command.sample;

public class Main {

    public static void main(String[] args) {

        TV tv = new TV();
        ICommand turnOn = new TurnTVOnCommand(tv);
        ICommand turnOff = new TurnTVOffCommand(tv);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOn);
        remote.pressButton();

        remote.setCommand(turnOff);
        remote.pressButton();
    }
}