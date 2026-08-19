package creational.abstractfactory.sample.elements.button;

import creational.abstractfactory.sample.interfaces.IButton;

public class DarkButton implements IButton {
    @Override
    public void render() {
        System.out.println("Rendering dark button");
    }
    
}
