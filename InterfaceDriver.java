public class InterfaceDriver {
    public static void main(String[] args) {
        Alive horse = new Horse("Moosy", 5, "Wild horse");
        horse.move("home");
        horse.makeSound();

        Alive train = new Train(4, "Downford Sons and Co.");
        train.move("Downing Street");
        train.makeSound();

        Animal duck = new Duck(4, 5, "Donaldo", "green");
        duck.move("Disney world");
        duck.makeSound();

        Avatar kus = new Avatar("Kus");
        kus.summon(5);
        kus.touch("tesseract");
        kus.help();

        Duck pollard = new Duck(5, 6, "Pollard", "red");
        pollard.summon(6);
        pollard.help();
        pollard.touch("bread");
    }
}
