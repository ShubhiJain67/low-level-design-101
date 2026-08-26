package structural.bridge.sample.colors;

import structural.bridge.sample.IColor;

public class RedColor implements IColor {
    @Override
    public String fill() {
        return "red";
    }
}
