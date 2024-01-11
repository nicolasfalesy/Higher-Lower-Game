package in_class_assignment;

import java.util.*;

public class HigherLower {
    public static void main(String[] args) throws InterruptedException { // thread.sleep was not working without
                                                                         // throwing interrupted exception
        // initilaizes scanner and variables
        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();
        int userNumber = 0;
        int numGuesses = 3;
        int rounds = 0;
        int round = 1;
        int score = 0;
        int i = 1;
        boolean stillPlaying = true;
        // for printing messages
        int textSpeed = 22;
        int messageLongDelay = 1100;
        int messageShortDelay = 225;

        // intro message for user (Explains the game)
        fancy_text("Welcome to my Higher Lower game!!", textSpeed, messageLongDelay);
        fancy_text("\nI'm thinking of a number between 1-15.", textSpeed, messageLongDelay);
        fancy_text("\nYou have " + numGuesses + " tries to guess it.", textSpeed, messageLongDelay);
        fancy_text("\nIf you guess the number correctly, you win!", textSpeed, messageLongDelay);
        fancy_text("\nIf you don't, I win!", textSpeed, messageLongDelay);

        /*
         * // testing purposes
         * randNum = 4;
         */

        // when actually playing the game (useful for restarting the game)
        boolean playing = true;
        while (playing) {
            fancy_text("\nHow many rounds would you like to play?: ", textSpeed, messageLongDelay);
            // makes sure the user inputs a number
            rounds = valid_number(userInput);
            // checks if the number is valid
            if (rounds <= 0) {
                fancy_text("Sorry, that is not valid. Please try playing at a later time.", textSpeed,
                        messageShortDelay);
                stillPlaying = false;
            } else {
                fancy_text("Okay, sounds good.", textSpeed, messageShortDelay);
            }

            // stillPlaying boolean is useful for skipping to the very end when the user
            // enters a 0 or negative number for rounds #
            while (stillPlaying) {
                // loops through the rounds
                for (round = 1; round <= rounds; round++) {
                    int randNum = rand.nextInt(15) + 1;
                    // a way for me to know approx what the number is (basically just
                    // cheating/rigging it for myself lol), plus it adds a fun element to the game
                    if (randNum <= 5) {
                        fancy_text("\nThis number is going to be difficult!", textSpeed, messageLongDelay);
                    } else if (randNum <= 10) {
                        fancy_text("\nDon't worry, this will be a breeze!", textSpeed, messageLongDelay);
                    } else {
                        fancy_text("\nYou got this!", textSpeed, messageLongDelay);
                    }

                    // asks user for the first number
                    fancy_text("\nROUND #" + round, textSpeed, messageShortDelay);
                    fancy_text("\nPlease enter your first guess between 1-15: ", textSpeed, messageShortDelay);

                    // the game
                    for (i = 1; i <= numGuesses; i++) {
                        // stores the number each round and checks if the number is valid
                        boolean numberValid = false;
                        while (!numberValid) {
                            numberValid = true;
                            userNumber = valid_number(userInput);
                            if (userNumber <= 0 || userNumber > 15) {
                                fancy_text("Sorry, that number is not valid. Please try again: ", textSpeed,
                                        messageLongDelay);
                                numberValid = false;
                            } else {
                                numberValid = true;
                                break;
                            }
                        } // ends while (numberValid)

                        // checks if it is the number
                        if (userNumber == randNum) {
                            fancy_text("Awh man, you got it right! The number was in fact " + randNum, textSpeed,
                                    messageLongDelay);
                            score++;
                            break;
                            // checks if it is the first - third guess
                        } else if (i < numGuesses) {
                            // checks if the number is too high
                            if (userNumber > randNum) {
                                fancy_text("Your number is too high, please try again: ", textSpeed, messageShortDelay);
                                // checks if the number is too low
                            } else if (userNumber < randNum) {
                                fancy_text("Your number is too low, please try again!: ", textSpeed, messageShortDelay);
                            }
                            // checks if it is the last guess
                        } else {
                            fancy_text("Sorry, you ran out of tries. The number was " + randNum
                                    + "! That means I win, HAHAHA 😂😂", textSpeed, messageLongDelay);
                            break;
                        }
                    } // ends for (game)

                } // ends for (rounds)

                // calculates and prints out the user's score
                double scorePercentage = ((double)score/rounds) * 100;
                fancy_text(
                        "\nLooks like you got " + score + " out of " + rounds + " correct!",
                        textSpeed, messageLongDelay);
                fancy_text("\nThat's " + String.format("%.2f", scorePercentage) + "%!", textSpeed, messageShortDelay);
                fancy_text("\nThanks for playing!", textSpeed, messageShortDelay);
                playing = false;
                stillPlaying = false;
            } // ends while (stillPlaying)
              // Asks user to play again
            fancy_text("\nWould you like to play again? (y/n): ", textSpeed, 0);
            String playingString = userInput.next();
            if (playingString.equalsIgnoreCase("yes") || playingString.equalsIgnoreCase("y")) {
                // resets everything
                score = 0;
                round = 1;
                i = 1;
                stillPlaying = true;
                playing = true;
                fancy_text("Ok, let's play again!", textSpeed, messageShortDelay);
            } else {
                fancy_text("Ok, thanks for playing!", textSpeed, messageShortDelay);
                System.exit(0);
            }
        } // ends while (playing)
        userInput.close();
    } // ends main

    // function to make the code simple to read
    public static void fancy_text(String message, int textSpeed, int ms) throws InterruptedException {
        for (int j = 0; j < message.length(); j++) {
            System.out.print(message.charAt(j));
            Thread.sleep(textSpeed);
        }
        Thread.sleep(ms);
    } // ends fancy_text

    public static int valid_number(Scanner scanner) {
        int validNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            validInput = true;
            try {
                validNumber = scanner.nextInt();
            } catch (InputMismatchException mismatch) {
                System.out.println("Please input an integer: ");
                scanner.nextLine();
                validInput = false;
            } // ends try/catch
        }
        return validNumber;
    } // ends valid_number
} // ends class