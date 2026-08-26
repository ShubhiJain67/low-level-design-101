package structural.adapter.sample.adapters;

import structural.adapter.sample.ICharger;
import structural.adapter.sample.chargers.LegacyCharger;

// Adapts LegacyCharger's oldPlug() to the ICharger interface charge() expects.
public class ChargerAdapter implements ICharger {
    private final LegacyCharger legacyCharger;

    public ChargerAdapter(LegacyCharger legacyCharger) {
        this.legacyCharger = legacyCharger;
    }

    @Override
    public void charge() {
        legacyCharger.oldPlug();
    }
}
