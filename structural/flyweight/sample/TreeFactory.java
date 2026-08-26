package structural.flyweight.sample;

import java.util.HashMap;
import java.util.Map;

// Caches one TreeType per (name, color) combination instead of creating a new one every time.
public class TreeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color) {
        String key = name + "-" + color;
        TreeType type = treeTypes.get(key);
        if (type == null) {
            type = new TreeType(name, color);
            treeTypes.put(key, type);
            System.out.println("Created new TreeType: " + key);
        }
        return type;
    }
}
