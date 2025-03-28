package com.example.GameHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.example.DisplayManager;
import com.example.InputHandler;
import com.example.UserHandling.Score;
import com.example.UserHandling.User;
import com.example.WordDataHandling.GameSessionWord;

import lombok.Data;


@Data
public class GameSession {

    private boolean revealWordFact = false;
    private int attemptsLeft = 6;
    private GameSessionWord selectedWord;
    private InputHandler inputHandler;
    private User user;
    private Set<Character> guessedLetters;
    private int multiplier;


   
    public GameSession(InputHandler inputHandler, GameSessionWord selectedWord, User user){
        this.inputHandler = inputHandler;
        this.selectedWord = selectedWord;
        this.user = user;
        this.multiplier = DifficultyManager.getDifficultyMultiplier(this.selectedWord.getDifficulty());
        this.guessedLetters = new HashSet<>();
    }




    public boolean play() {
        while (this.attemptsLeft > 0 && !isWordGuessed()) {
            DisplayManager.displayWordProgress(selectedWord.getWord().getName(), guessedLetters);
            if(revealWordFact){
                DisplayManager.displayWordFact(selectedWord.getWord().getHint());
            }
            DisplayManager.displayPlayerHud(this.attemptsLeft, this.user.getPoints());

            String input =inputHandler.getLetterOrHint();
            
            if (input.equals("hint")) {
                useHintSession();
                continue;
            }

            if(input.equals("cancel")){
                System.out.println("The score earned will be lost except points");
                resetScore();
                return false;
            }
            

            if (input.length() == 1) {
                char guess = input.charAt(0);
                processGuess(guess);
            } else {
                System.out.println("Invalid input. Enter a single letter.");
            }

              
        }

        return checkGameResult();
        
    }

    private void resetScore(){
        this.user.setCurrScore(new Score());
    }

    private boolean checkGameResult() {
        if (isWordGuessed()) {
            System.out.println("🎉 Congratulations! You guessed the word: " + selectedWord.getWord().getName());
            System.out.println("Your final score: " + user.getCurrScore().getScoreValue());

            // Update high score if needed
            if (user.getHighScore() == null || user.getCurrScore().getScoreValue() > user.getHighScore().getScoreValue()) {
                user.setHighScore(user.getCurrScore());
                System.out.println("New High Score! 🎯");
            }
            return true;
        } else {
            System.out.println("Game over! The word was: " + selectedWord.getWord().getName());
            resetScore();
            return false;
        }
    }

    





    private void processGuess(char guess) {
        if (!isValidGuess(guess)) {
            System.out.println("Invalid or repeated guess. Try again.");
            return;
        }

        guessedLetters.add(guess);

        if (this.selectedWord.getWord().getName().contains(String.valueOf(guess))) {
            System.out.println("Correct guess!");
            

            int scoreIncrease = 10 * multiplier;
            int pointsIncrease = 3 * multiplier;
            this.user.addPoints(pointsIncrease);
            user.getCurrScore().addScore(scoreIncrease,  new Date(System.currentTimeMillis())); // Update user's score
        } else {
            System.out.println("Wrong guess!");
            attemptsLeft--;
        }

    }


    private boolean isValidGuess(char guess) {
        return Character.isLetter(guess) && !guessedLetters.contains(guess);
    }


    private void useHintSession(){
        DisplayManager.hintOptions();
        String input = inputHandler.getStringInt();
        
        // reveal letter
        if(input.equals("1")){
            if(HintSystem.canRevealLetter(this.user.getPoints())){
            revealRandomLetterHint();
            this.user.setPoints(this.user.getPoints() - HintSystem.getRevealRandomWordValue());
        }
        else{
            System.out.println("You don't have enough points to use this hint.");
            }
            
        }
        
        //  ADD EXTRA GUESS
        else if(input.equals("2")){
            if(HintSystem.canRemoveWrongLetter(this.user.getPoints())){
                this.attemptsLeft++;
                this.user.setPoints(this.user.getPoints() - HintSystem.getRemoveWrongLetterValue());
        }
            else{
                System.out.println("You don't have enough points to use this hint.");
            }
    }

        // reveal fact
        else if(input.equals("3")){
            if(!this.revealWordFact){
                if(HintSystem.canRevealWordFact(this.user.getPoints())){
                    this.revealWordFact = true;
                    this.user.setPoints(this.user.getPoints() - HintSystem.getRevealWordFactValue());
                }
                else{
                    System.out.println("You don't have enough points to use this hint.");
                    }
                
            }
            else{
                System.out.println("The fact is already revealed");
            }
            

        }

    }
    
    private void revealRandomLetterHint(){
            char hintLetter = getUnrevealedLetter();
            guessedLetters.add(hintLetter);
            System.out.println("Hint: The letter '" + hintLetter + "' is in the word.");

    }

    private char getUnrevealedLetter(){

         List<Character> unrevealedLetters = new ArrayList<>();

    for (char letter : this.selectedWord.getWord().getName().toCharArray()) {
        if (!guessedLetters.contains(letter)) {
            unrevealedLetters.add(letter);
        }
    }

    if (!unrevealedLetters.isEmpty()) {
        Random random = new Random();
        return unrevealedLetters.get(random.nextInt(unrevealedLetters.size())); // Pick a random letter
    }

    return '_';

    }


    private boolean isWordGuessed() {
       for (char letter : selectedWord.getWord().getName().toCharArray()){
        if (!this.guessedLetters.contains(letter)){
            return false;
       }
       }
       return true;
    }



}
