public class Salamander implements ColorChanging {
    private String name, color;
    public Salamander(String name, String color) {
        this.name = name;
        this.color = color;
    }
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

    @Override
    public void changeColor() {        
    }
    @Override
    public void changeColor(String c) {
        color = c;
    }
    
}
