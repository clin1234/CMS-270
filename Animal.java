/**
 * @author Charlie Lin
 * @since 10/20/2021
 * Hang Out Lab
 */

public abstract class Animal {
    private String name, color;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal(String name) {
        this.name = name;
    }

    public void walk() {
        System.out.println("I %s am walking".formatted(name));
    }

    public abstract void makeSound();
}
