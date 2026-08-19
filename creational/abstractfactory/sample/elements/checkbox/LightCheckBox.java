package creational.abstractfactory.sample.elements.checkbox;

import creational.abstractfactory.sample.interfaces.ICheckBox;

public class LightCheckBox implements ICheckBox {
    @Override
    public void render() {
        System.out.println("Rendering light checkbox");
    }
    
}
