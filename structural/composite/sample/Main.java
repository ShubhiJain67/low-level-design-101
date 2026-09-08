package structural.composite.sample;

public class Main {
    public static void main(String[] args) {
        Folder root = new Folder("root");
        Folder src = new Folder("src");
        File mainFile =  new File("Main.java");
        File utilsFile =  new File("Utils.java");
        File licenseFile =  new File("LICENSE.java");
        File readmeFile =  new File("README.java");
        
        src.add(mainFile);
        src.add(utilsFile);
        
        root.add(src); // Folder can contain other Folders and Files
        root.add(licenseFile);
        root.add(readmeFile);

        root.showDetails("");

    }
}
