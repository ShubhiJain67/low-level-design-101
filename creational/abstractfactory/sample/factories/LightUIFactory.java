package creational.abstractfactory.sample.factories;

import creational.abstractfactory.sample.interfaces.*;
import creational.abstractfactory.sample.elements.button.*;
import creational.abstractfactory.sample.elements.checkbox.*;

public class LightUIFactory implements IUIFactory {
    @Override
    public IButton renderButton() {
        return new LightButton();
    }

    @Override
    public ICheckBox renderCheckBox() {
        return new LightCheckBox();
    }
}
