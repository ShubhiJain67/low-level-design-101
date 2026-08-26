package structural.proxy.sample;

// Virtual proxy — defers the expensive RealImage construction until display() is actually called.
public class ProxyImage implements IImage {
    private final String filename;
    private RealImage realImage;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    // This can be created using singleton pattern as well, 
    // but for simplicity, we are creating a new instance of RealImage every time display() is called.
    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}
