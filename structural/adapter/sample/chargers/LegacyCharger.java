package structural.adapter.sample.chargers;

// Existing class with an incompatible interface — can't be changed.
public class LegacyCharger {
    public void oldPlug() {
        System.out.println("Charging via legacy 2-pin plug");
    }
}
