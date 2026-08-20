package creational.prototype.sample;

import java.util.ArrayList;

public class ShallowCopyClass implements Cloneable {
    private String name;
    private int age;
    private ArrayList<String> hobbies;

    public ShallowCopyClass(String name, int age, ArrayList<String> hobbies) {
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
    public Object clone() throws CloneNotSupportedException {
        ShallowCopyClass newObj = (ShallowCopyClass) super.clone();
        return newObj;
    }

}
