/**
 * @author Charlie Lin
 * @since 10/20/2021
 * Lab 6
 */

public abstract class Animal implements Alive{
    private String name;
    private int age;

    public Animal(String name, int a) {
        this.name = name;
        age = a;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int move(String s) {
        System.out.println("Moving to " + s + " takes " + s.length() + " moves.");
        return s.length();
    }
}
