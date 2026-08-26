package structural.composite.sample;

public class File implements IFileSystemComponent {
    private final String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "File: " + name);
    }
}
