package com.example;

import java.util.ArrayList;
import java.util.Set;

import com.example.GameHandler.HintSystem;
import com.example.UserHandling.User;

public class DisplayManager {
   


    public static void showMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Play");
        System.out.println("2. Pick User");
        System.out.println("3. View Stats");
        System.out.println("0. Exit\n");
    }

    public static void showUserMenu() {
        
        System.out.println("\nUser Management:");
        System.out.println("1. Create User");
        System.out.println("2. Load User");
        System.out.println("3. Delete User");
        System.out.println("4. View Current User");
        System.out.println("0. Menu\n");
    }

    public static void showOptions(ArrayList<String> options,  boolean isBackOption, boolean isMenuOption, String prompt) {

        if(!prompt.isEmpty())System.out.println(prompt);
            
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
                }

                if(isMenuOption) {
                    System.out.println("0. Menu");
                    }
                    if(isBackOption) {
                        System.out.println("99. Back");
                    }
    }

    public static void showUser(User user) {
        System.out.println("\nUser Information:\n");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Points: " + user.getPoints());
        System.out.println("Highscore: "+user.getHighScore().getScoreValue()+"\n");
       
    }

    public static void showPlayMenu() {
        
        System.out.println("\nPlay:");
        System.out.println("1. START");
        System.out.println("2. View Available Categories");
        System.out.println("3. View Rules");
        System.out.println("0. Menu\n");

    }

    public static void displayWordProgress(String wordToGuess, Set<Character> guessedLetters) {
        StringBuilder displayedWord = new StringBuilder();
        for (char letter : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(letter)) {
                displayedWord.append(letter).append(" ");
            } else {
                displayedWord.append("_ ");
            }
        }
        System.out.println("Word: " + displayedWord.toString().trim());



    }

    public static void displayPlayerHud(int attemptsLeft, int points) {
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.println("Points: " + points); // Show user points
            System.out.println("Enter a letter, or type 'hint' (You will pick a hint type)");
    }

    public static void displayWordFact(String hint) {
        System.out.println("Fact about: "+hint);
    
    }

    public static void hintOptions() {
        System.out.println("\nHint Options:");
        System.out.println("1. Reveal a random letter(costs "+ HintSystem.getRevealRandomWordValue() + " points)");
        System.out.println("2. Add an extra random word(costs "+ HintSystem.getRevealRandomWordValue() + " points)");
        System.out.println("3. Reveal a fact(costs "+ HintSystem.getRevealWordFactValue() + " points)");


        
    }

    public static void continuePlay() {
        System.out.println("\nDo you want to continue playing with the current category and difficulty to increase score?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    public static void showRules(){
        System.out.println("\n📜 HANGMAN RULES 📜");
        System.out.println("1️⃣ The game randomly selects a word.");
        System.out.println("2️⃣ You must guess one letter at a time.");
        System.out.println("3️⃣ If the letter is correct, it is revealed.");
        System.out.println("4️⃣ If incorrect, you lose an attempt.");
        System.out.println("6️⃣ The game ends when you guess the word or run out of attempts.");
    }

    public static void display(ArrayList<String> categoryNames) {
        System.out.println("\nAvailable Categories: ");
        for (int i = 0; i < categoryNames.size(); i++) {
            System.out.println((i+1) + " " + categoryNames.get(i));
            }
    }

    public static void showTopFiveLeaderboard(ArrayList<String> topFiveLeaderboard) {
        System.out.println("\nTop 5 Leaderboard: \n");
        for (int i = 0; i < topFiveLeaderboard.size(); i++) {
            System.out.println((i+1) + " " + topFiveLeaderboard.get(i));
            }
    }

}
