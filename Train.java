public class Train implements Alive {
    private int numCars;
    private String company;

    public Train(int numCars, String company) {
        this.numCars = numCars;
        this.company = company;
    }

    public int getNumCars() {
        return numCars;
    }

    public void setNumCars(int numCars) {
        this.numCars = numCars;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public void makeSound() {
        System.out.println("Choo choo");

    }

    @Override
    public int move(String s) {
        System.out.println("Transport to " + s + " is sponsored by " + company);
        return 0;
    }

    public void goInNightMode() {
        System.out.println("Darkening sky... We will still arrive on time");
    }

}
