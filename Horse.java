public class Horse extends Animal {
    private String breed;

    public Horse(String name, int a, String breed) {
        super(name, a);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void eat() {
        System.out.println("Munching on grass");
    }

    @Override
    public void makeSound() {
        System.out.println("Neigh");
    }
}
