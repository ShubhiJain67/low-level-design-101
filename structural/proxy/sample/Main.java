package structural.proxy.sample;

public class Main {
    public static void main(String[] args) {
        IImage image = new ProxyImage("photo.png");

        System.out.println("Image object created, nothing loaded yet.");
        image.display(); // loads from disk now
        image.display(); // reuses already-loaded RealImage
    }
}
