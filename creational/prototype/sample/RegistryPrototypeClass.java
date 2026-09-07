package creational.prototype.sample;

import java.util.ArrayList;

// Deep-copying Prototype implementation, dedicated to the registry demo —
// kept separate from DeepCopyClass so that file's original shallow-vs-deep
// bug demo stays untouched.
// Cloneable is only a marker does not make it class to override the clone() method
public class RegistryPrototypeClass implements CloneablePrototype, Cloneable {
    private final String name;
    private final int age;
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
