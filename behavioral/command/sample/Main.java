package behavioral.command.sample;

public class Main {

    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();

        TV tv1 = new TV("TV 1");
        ICommand turnOnV1 = new TurnTVOnCommand(tv1);
        ICommand turnOffV1 = new TurnTVOffCommand(tv1);
        remote.setCommand(turnOnV1);
        remote.pressButton();
        remote.setCommand(turnOffV1);
        remote.pressButton();

        TV tv2 = new TV("TV 2");
        ICommand turnOnV2 = new TurnTVOnCommand(tv2);
        ICommand turnOffV2 = new TurnTVOffCommand(tv2);
        remote.setCommand(turnOnV2);
        remote.pressButton();
        remote.setCommand(turnOffV2);
        remote.pressButton();
    }
}