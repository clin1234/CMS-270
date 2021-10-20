public class SAccount {
    private String name;
    private String superpower;
    private int lives;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSuperpower() {
        return superpower;
    }
    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }
    public int getLives() {
        return lives;
    }
    public void setLives(int lives) {
        this.lives = lives;
    }
    public SAccount(String name, String superpower, int lives) {
        this.name = name;
        this.superpower = superpower;
        this.lives = lives;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Number of lives: " + lives + ", Superpower: " + superpower;
    }
}
