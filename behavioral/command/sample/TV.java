package behavioral.command.sample;

public class TV {
    private final String name;

    TV(String name){
        this.name = name;
    }

    public void turnOn() {
        System.out.println("Turning " + this.name + " ON");
    }

    public void turnOff() {
        System.out.println("Turning " + this.name + " OFF");
    }
}