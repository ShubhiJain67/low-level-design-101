package creational.prototype.sample;

import java.util.ArrayList;

// Deep-copying Prototype implementation, dedicated to the registry demo —
// kept separate from DeepCopyClass so that file's original shallow-vs-deep
// bug demo stays untouched.
public class RegistryPrototypeClass implements Prototype, Cloneable {
    private String name;
    private int age;
    private ArrayList<String> hobbies;

    public RegistryPrototypeClass(String name, int age, ArrayList<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    @Override
    public RegistryPrototypeClass clone() throws CloneNotSupportedException {
        RegistryPrototypeClass newObj = (RegistryPrototypeClass) super.clone();
        newObj.hobbies = new ArrayList<>(this.hobbies);
        return newObj;
    }
}
