package structural.flyweight.sample;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Tree> forest = new ArrayList<>();

        forest.add(new Tree(1, 2, TreeFactory.getTreeType("Oak", "Green")));
        forest.add(new Tree(3, 4, TreeFactory.getTreeType("Oak", "Green"))); // reuses cached TreeType
        forest.add(new Tree(5, 6, TreeFactory.getTreeType("Pine", "DarkGreen")));

        for (Tree tree : forest) {
            tree.draw();
        }
    }
}
