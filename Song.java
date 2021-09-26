/**
 * @author Alex Billini and Charlie Lin
 * @since 9/20/2021
 */
// Lab 3
public class Song {
    private String name, composer;
    private int runtime;

    public Song(String name, String composer, int runtime) {
        this.name = name;
        this.composer = composer;
        this.runtime = runtime;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComposer() {
        return composer;
    }
    public void setComposer(String composer) {
        this.composer = composer;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    void play() {
        System.out.println("Playing " + name);
    }  
}
