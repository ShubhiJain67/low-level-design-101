package creational.abstractfactory.sample.elements.button;

import creational.abstractfactory.sample.interfaces.IButton;

public class LightButton implements IButton {
    @Override
    public void render() {
        System.out.println("Rendering light button");
    }
    
}
