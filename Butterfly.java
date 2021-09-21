/**
 * @author Charlie Lin
 * @since 9/15/2021
 * Assignment 1
 */
public class Butterfly {
    private String topColor, bottomColor;
    private final int numberOfWings;
    private static int numCreated;
    private boolean state;

    // Constructors

    public Butterfly() {
        numberOfWings = 8;
        numCreated++;
    }

    public Butterfly(String top, String bottom, int numberofPairs) {
        topColor = top;
        bottomColor = bottom;
        numberOfWings = numberofPairs * 2;
        numCreated++;
    }

    // Getters and setters

    public String getTopColor() {
        return topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }

    public String getBottomColor() {
        return bottomColor;
    }

    public void setBottomColor(String bottomColor) {
        this.bottomColor = bottomColor;
    }

    public int getNumberOfWings() {
        return numberOfWings;
    }

    public static int getNumCreated() {
        return numCreated;
    }

    public static void setNumCreated(int num) {
        numCreated = num;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    // Other methods

    public void molt(String top, String bottom) {
        bottomColor = bottom;
        topColor = top;
    }

    public void fly() {
        state = true;
        for (int i = 0; i < 3; i++)
            System.out.print(bottomColor + " " + topColor + " ");
        System.out.println();
    }

    public void rest() {
        state = false;
        System.out.println(bottomColor);
    }

    public void fluoresce() {
        System.out.println("See my beautiful " + (bottomColor == "white" ? topColor : "fuchsia") + " light.");
    }
}
