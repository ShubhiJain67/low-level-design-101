package creational.prototype.sample;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ShallowCopyClass shallowOriginal = new ShallowCopyClass("Alice", 30, new ArrayList<String>());
            ShallowCopyClass shallowClone = (ShallowCopyClass) shallowOriginal.clone();
            shallowClone.getHobbies().add("Reading");
            System.out.println("Original hobbies: " + shallowOriginal.getHobbies());
            System.out.println("Shallow clone hobbies: " + shallowClone.getHobbies());


            DeepCopyClass deepOriginal = new DeepCopyClass("Alice", 30, new ArrayList<String>());
            DeepCopyClass deepCopy = (DeepCopyClass) deepOriginal.clone();
            deepCopy.getHobbies().add("Reading");
            System.out.println("Original hobbies: " + deepOriginal.getHobbies());
            System.out.println("Deep copy hobbies: " + deepCopy.getHobbies());


            // Prototype Registry — client asks for a clone by key, never touches a concrete class directly.
            PrototypeRegistry registry = new PrototypeRegistry();
            registry.register("default-user", new RegistryPrototypeClass("Bob", 25, new ArrayList<>()));

            RegistryPrototypeClass fromRegistry1 = (RegistryPrototypeClass) registry.get("default-user");
            fromRegistry1.getHobbies().add("Chess");
            RegistryPrototypeClass fromRegistry2 = (RegistryPrototypeClass) registry.get("default-user");

            System.out.println("Registry clone 1 hobbies: " + fromRegistry1.getHobbies());
            System.out.println("Registry clone 2 hobbies: " + fromRegistry2.getHobbies());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
