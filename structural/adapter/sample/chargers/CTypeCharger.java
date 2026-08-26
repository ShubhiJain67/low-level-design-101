package structural.adapter.sample.chargers;

import structural.adapter.sample.ICharger;

public class CTypeCharger implements ICharger {
    public void charge() {
        System.out.println("Charging with Type-C charger");
    }
}
