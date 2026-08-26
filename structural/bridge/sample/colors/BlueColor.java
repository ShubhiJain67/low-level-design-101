package structural.bridge.sample.colors;

import structural.bridge.sample.IColor;

public class BlueColor implements IColor {
    @Override
    public String fill() {
        return "blue";
    }
}
