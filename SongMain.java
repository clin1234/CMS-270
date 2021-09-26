/**
 * @author Alex Billini and Charlie Lin
 * @since 9/20/2021
 */
// Lab 3
import static java.lang.System.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        out.println("Input list of songs in the following format: name, composer, runtime (in seconds). "
                + "Separate the parts with commas, one song per line.");
        try (Scanner scan = new Scanner(in)) {
            while (scan.hasNextLine()) {
                String[] splitLine = scan.nextLine().split(",");
                /* above array contains three elements in the following order:
                name, composer, and runtime. */
                playlist.add(new Song(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2])));
            }
        }
        playSongs();
    }

    static ArrayList<Song> playlist = new ArrayList<Song>();

    static void playSongs() {
        /* Create a range from 0 to playlist's size (exclusive), then
        convert above range into a List<Integer> containing all indices within playlist.
        This substitutes calling a constructor of List<Integer>, and then using a for loop
        from i = 0 to the size of playlist (exclusive) to call tmp.set(i, i),
        where i is the index of the playlist array. */
        IntStream tmpRange = IntStream.range(0, playlist.size());
        // List<Integer> tmp = tmpRange.boxed().toList(); for Java SE 16 or later
        List<Integer> tmp = tmpRange.boxed().collect(Collectors.toList());
        while (!tmp.isEmpty()) {
            // Use ThreadLocalRandom instead of Random so that we don't need to create a Random object ourselves.
            int randomInt = ThreadLocalRandom.current().nextInt(tmp.size());
            int idx = tmp.remove(randomInt);
            playlist.get(idx).play();
        }
    }
}
