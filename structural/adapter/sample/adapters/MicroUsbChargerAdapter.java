package structural.adapter.sample.adapters;

import structural.adapter.sample.ICharger;
import structural.adapter.sample.chargers.LegacyMicroUsbCharger;

// Adapts LegacyMicroUsbCharger's plugMicroUsb() to the ICharger interface charge() expects.
public class MicroUsbChargerAdapter implements ICharger {
    private final LegacyMicroUsbCharger legacyMicroUsbCharger;

    public MicroUsbChargerAdapter(LegacyMicroUsbCharger legacyMicroUsbCharger) {
        this.legacyMicroUsbCharger = legacyMicroUsbCharger;
    }

    @Override
    public void charge() {
        legacyMicroUsbCharger.plugMicroUsb();
    }
}
