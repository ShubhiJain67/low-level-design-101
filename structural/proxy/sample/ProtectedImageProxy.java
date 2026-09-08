package structural.proxy.sample;

public class ProtectedImageProxy implements IImage {     
    private final String filename;
    private final String userRole;
    private RealImage realImage;

    public ProtectedImageProxy(String filename, String userRole) {
        this.filename = filename;
        this.userRole = userRole;
    }
    
    @Override
    public void display() {
        if (!"ADMIN".equals(userRole)) {
            System.out.println( "Access denied. Only ADMIN can view this image." );
            return;
        } 
        if (realImage == null) {
            realImage = new RealImage(filename);
        } 
        realImage.display();
    }
}