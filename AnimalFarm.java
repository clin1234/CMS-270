/**
 * @author Charlie Lin
 * @since 10/20/2021
 * 
 * Lab 6
 */
public class AnimalFarm {
    public static void walkTheAnimal(Animal a, int x) {
        for (var i = 0; i < x; i++);
            //a.walk();
    }

    public static void main(String[] args) {
        // var a = new Animal("Stinky");
        // a.setAge(3); a.setColor("green");
        //var d = new Duck(3, "Daffy", "yellow");
        var c = new Cow(10, "Mabel", "angus");
        // a.walk();
        //d.walk();
        //c.walk();
        c.walk(12);

        //Animal a = new Duck(4, "Stinky", "red");
        Animal ac = new Cow(5, "Trol", "holstein");

        //walkTheAnimal(d, 3);
        walkTheAnimal(c, 5);
        //walkTheAnimal(a, 2);
        walkTheAnimal(ac, 2);

        /* Doesn't work due to static binding:
        because there's no instance method called
        swim in the Animal class, a's declared type,
        compilation fails. */
        // a.swim();
    }
}
