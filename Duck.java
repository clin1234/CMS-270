/**
 * @author Charlie Lin
 * @since 10/20/2021
 * Hang Out Lab
 */
public class Duck extends Animal implements CommonBehavior{
    private int wingSpan;

    public int getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }


    public Duck(int wingSpan, int age, String name, String billColor) {
        super(name, age);
        this.wingSpan = wingSpan;
    }
    public void makeSound() {
        System.out.println("Quack");

    }

    public void fly() {
        System.out.println("Flying is fun! Wheee");
    }

    @Override
    public void touch(String t) {
       System.out.println("Apparently this duck is fond of touching "+t); 
    }

    @Override
    public void help() {
        System.out.println("A small duck has arrived! What help do you need?");
    }
    public void summon(int c) {
        System.out.println("A horde of "+c*2+" ducks arrived from a different dimension.");
    }
}
