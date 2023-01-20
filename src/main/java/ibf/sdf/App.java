package ibf.sdf;

import java.util.Random;
import java.util.Scanner;

// CREATE CLIENT SERVER FOR THIS GAME

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Integer guessNumber = rand.nextInt(100);
        Integer myGuess = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (myGuess != guessNumber) {
                System.out.println("Guess your number!");
                System.out.print("> ");
                myGuess = scanner.nextInt();
                if (myGuess < guessNumber) {
                    System.out.println("A bit higher.");
                } else if (myGuess > guessNumber) {
                    System.out.println("A bit lower.");
                } else {
                    System.out.println("Nice! You got it ");
                    System.exit(0);
                }
            }
        }
    }
}
