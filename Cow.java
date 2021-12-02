/**
 * @author Charlie Lin
 * @since 10/20/2021
 * Hang Out Lab
 */
public class Cow extends Animal {
    private int numSpots;
    private String breed;

    public int getNumSpots() {
        return numSpots;
    }

    public void setNumSpots(int numSpots) {
        this.numSpots = numSpots;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Cow(int numSpots, String name, String breed) {
        super(name,0);
        this.numSpots = numSpots;
        this.breed = breed;
    }

    public void walk(int n) {
        System.out.println(String.format("I am %s I am sauntering %d times", getName(), n));
    }

    @Override
    public void makeSound() {
        System.out.println("Moo");
    }

}
