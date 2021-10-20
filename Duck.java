/**
 * @author Charlie Lin
 * @since 10/20/2021
 * Hang Out Lab
 */
public class Duck extends Animal {
    private int wingSpan;
    private String billColor;

    public int getWingSpan() {
        return wingSpan;
    }

    public void setWingSpan(int wingSpan) {
        this.wingSpan = wingSpan;
    }

    public String getBillColor() {
        return billColor;
    }

    public void setBillColor(String billColor) {
        this.billColor = billColor;
    }

    public Duck(int wingSpan, String name, String billColor) {
        super(name);
        this.wingSpan = wingSpan;
        this.billColor = billColor;
    }

    @Override
    public void walk() {
        System.out.println("I am %s, I am waddling!".formatted(getName()));
    }

    @Override
    public void makeSound() {
        System.out.println("Quack");

    }

}
