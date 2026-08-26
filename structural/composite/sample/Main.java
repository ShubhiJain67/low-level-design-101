package structural.composite.sample;

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder src = new Folder("src");
        src.add(new File("Main.java"));
        src.add(new File("Utils.java"));
        root.add(src); // Folder can contain other Folders and Files
        root.add(new File("LICENSE"));
        root.add(new File("README.md"));

        root.showDetails(""); // same call works on File and Folder alike
    }
}
