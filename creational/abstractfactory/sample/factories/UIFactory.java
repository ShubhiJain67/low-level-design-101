package creational.abstractfactory.sample.factories;

import creational.abstractfactory.sample.UIType;
import creational.abstractfactory.sample.interfaces.IUIFactory;

public class UIFactory {
    
    // Picks the concrete factory for a given theme. Both the button and
    // checkbox below come from this ONE factory instance, so they're
    // guaranteed to belong to the same family — that's the actual guarantee
    // Abstract Factory buys you over calling two separate factories.
    public static IUIFactory getUIFactory(UIType type) {
        switch (type) {
            case LIGHT -> {
                return new LightUIFactory();
            }
            case DARK -> {
                return new DarkUIFactory();
            }
            default -> throw new IllegalArgumentException("Invalid UI type");
        }
    }
}
