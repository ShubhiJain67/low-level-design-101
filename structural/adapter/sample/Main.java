package structural.adapter.sample;

import structural.adapter.sample.adapters.ChargerAdapter;
import structural.adapter.sample.adapters.MicroUsbChargerAdapter;
import structural.adapter.sample.chargers.CTypeCharger;
import structural.adapter.sample.chargers.LegacyCharger;
import structural.adapter.sample.chargers.LegacyMicroUsbCharger;

public class Main {
    public static void main(String[] args) {
        // Already compatible — implements ICharger directly, no adapter needed.
        ICharger modernCharger = new CTypeCharger();
        modernCharger.charge();

        // Legacy devices, each with a different incompatible interface, each wrapped by its own adapter.
        ICharger charger1 = new ChargerAdapter(new LegacyCharger());
        charger1.charge();

        ICharger charger2 = new MicroUsbChargerAdapter(new LegacyMicroUsbCharger());
        charger2.charge();
    }
}
