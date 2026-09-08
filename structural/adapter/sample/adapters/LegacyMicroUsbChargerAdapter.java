package structural.adapter.sample.adapters;

import structural.adapter.sample.ICharger;
import structural.adapter.sample.chargers.LegacyMicroUsbCharger;

// Adapts LegacyMicroUsbCharger's plugMicroUsb() to the ICharger interface charge() expects.
public class LegacyMicroUsbChargerAdapter implements ICharger {
    private final LegacyMicroUsbCharger legacyMicroUsbCharger;

    public LegacyMicroUsbChargerAdapter(LegacyMicroUsbCharger legacyMicroUsbCharger) {
        this.legacyMicroUsbCharger = legacyMicroUsbCharger;
    }

    @Override
    public void charge() {
        legacyMicroUsbCharger.plugMicroUsb();
    }
}
