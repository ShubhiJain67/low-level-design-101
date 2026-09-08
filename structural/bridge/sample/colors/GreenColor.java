package structural.bridge.sample.colors;

import structural.bridge.sample.IColor;

public class GreenColor implements IColor {
    @Override
    public String fill() {
        return "green";
    }
}
