/*
* Name: Nicolas Falesy
* Course: ICS3C
* Teacher: Mrs. McCaffery
* Date: 10/16/2023
* Description: Tells user to guess a nubmer between 1-15 and tells them if they are right or wrong (They have 4 tries)
* Inspiration: From Andrew Broderick, he's very helpful - He taught me how to use try/catch and while loops
*/

import java.util.*;

public class HigherLower {
    public static void main(String[] args) throws InterruptedException { // thread.sleep was not working without
                                                                         // throwing interrupted exception
        // initilaizes scanner and variables
        Scanner userInput = new Scanner(System.in);
        Random rand = new Random();
        int userNumber = 0;
        int numGuesses = 4;
        int rounds = 0;
        int score = 0;
        int i = 1;
        int round = 1;
        // for printing messages
        int textSpeed = 22;
        int messageLongDelay = 1100;
        int messageShortDelay = 225;

        // intro message for user (Explains the game)
        fancy_text("Welcome to my Higher Lower game!!", textSpeed, messageLongDelay);
        fancy_text("\nI'm thinking of a number between 1-15.", textSpeed, messageLongDelay);
        fancy_text("\nYou have 4 tries to guess it.", textSpeed, messageLongDelay);
        fancy_text("\nIf you guess the number correctly, you win!", textSpeed, messageLongDelay);
        fancy_text("\nIf you don't, I win!", textSpeed, messageLongDelay);

        /*
         * // testing purposes
         * randNum = 4;
         */

        // playing the game
        boolean playing = true;
        while (playing) {
            fancy_text("\nHow many rounds would you like to play?: ", textSpeed, messageLongDelay);
            // makes sure the user inputs a number
            boolean isNumber = false;
            while (!isNumber) {
                isNumber = true;
                try {
                    rounds = userInput.nextInt();
                } catch (InputMismatchException mismatch) {
                    fancy_text("Please input an integer: ", textSpeed, messageShortDelay);
                    userInput.nextLine();
                    isNumber = false;
                }
            } // ends while
              // checks if the number is valid
            if (rounds <= 0) {
                fancy_text("Sorry, that number is not valid. Please try playing at a later time.", textSpeed,
                        messageShortDelay);
                System.exit(0);
            } else {
                fancy_text("Okay, sounds good.", textSpeed, messageShortDelay);
            }

            // loops through the rounds
            for (round = 1; round <= rounds; round++) {
                int randNum = rand.nextInt(15) + 1;
                // asks user for the first number and stores it
                fancy_text("\nROUND #" + round, textSpeed, messageShortDelay);
                fancy_text("\nPlease enter your first guess between 1-15: ", textSpeed, messageShortDelay);

                // the game
                for (i = 1; i <= numGuesses; i++) {
                    // makes sure the user inputs a number
                    isNumber = false;
                    while (!isNumber) {
                        isNumber = true;
                        try {
                            userNumber = userInput.nextInt();
                        } catch (InputMismatchException mismatch) {
                            fancy_text("Please input an integer: ", textSpeed, messageShortDelay);
                            userInput.nextLine();
                            isNumber = false;
                        }
                    } // ends while

                    // checks if the number is valid
                    if (userNumber < 0 || userNumber > 15) {
                        fancy_text("Sorry, that number is not valid. Please try playing at a later time.", textSpeed,
                                messageLongDelay);
                        System.exit(0);
                        // checks if it is the number
                    } else if (userNumber == randNum) {
                        fancy_text("Awh man, you got it right! The number was infact " + randNum, textSpeed,
                                messageLongDelay);
                        score++;
                        break;
                        // checks if it is the first - third guess
                    } else if (i < 4) {
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

            // prints out the user's score
            double scorePercentage = score / rounds * 100;
            fancy_text(
                    "\nLooks like you got " + score + " out of " + rounds + " correct!\nThat's "
                            + String.format("%.2f", scorePercentage) + "%!\nThanks for playing!",
                    textSpeed, messageLongDelay);
            playing = false;

            // Asks user to play again
            fancy_text("\nWould you like to play again? (y/n): ", textSpeed, 0);
            String playingString = userInput.next();
            if (playingString.equalsIgnoreCase("yes") || playingString.equalsIgnoreCase("y")) {
                playing = true;
                score = 0;
                round = 1;
                i = 1;
                fancy_text("Ok, let's play again!", textSpeed, messageShortDelay);
            } else {
                fancy_text("Ok, thanks for playing!", textSpeed, messageShortDelay);
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
} // ends class