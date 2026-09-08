package structural.adapter.sample;

import structural.adapter.sample.adapters.*;
import structural.adapter.sample.chargers.*;

public class Main {
    public static void main(String[] args) {
        // Already compatible — implements ICharger directly, no adapter needed.
        ICharger modernCharger = new CTypeCharger();
        modernCharger.charge();

        // Legacy devices, each with a different incompatible interface, each wrapped by its own adapter.
        ICharger charger1 = new LegacyChargerAdapter(new LegacyCharger());
        charger1.charge();

        ICharger charger2 = new LegacyMicroUsbChargerAdapter(new LegacyMicroUsbCharger());
        charger2.charge();
    }
}
