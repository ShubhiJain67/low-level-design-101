package structural.adapter.sample.chargers;

import structural.adapter.sample.ICharger;

public class CTypeCharger implements ICharger {
    @Override 
    public void charge() {
        System.out.println("Charging with Type-C charger");
    }
}
