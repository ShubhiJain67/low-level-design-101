package structural.composite.sample;

import java.util.ArrayList;
import java.util.List;

public class Folder implements IFileSystemComponent {
    private final String name;
    private final List<IFileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(IFileSystemComponent component) {
        children.add(component);
    }

    @Override
    public void showDetails(String indent) {
        System.out.println(indent + "Folder: " + name);
        for (IFileSystemComponent child : children) {
            child.showDetails(indent + "  ");
        }
    }
}
