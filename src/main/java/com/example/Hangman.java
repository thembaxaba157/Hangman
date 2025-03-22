package com.example;

import com.example.State.GameState;
import com.example.WordDataHandling.WordManager;

public class Hangman {
    private GameState currGameState;
    private WordManager wordManager;
    private InputHandler inputHandler;

    public Hangman() {
        this.inputHandler = new InputHandler();
        this.wordManager = new WordManager();
        this.currGameState = GameState.MENU;
        

    }

    public void run() {
        while (this.currGameState != GameState.EXIT) {
            switch (this.currGameState) {
                case MENU:
                    menuSession();
                    System.out.println("current state "+ this.currGameState.toString());
                    break;

                case PICK_USER:
                    pickUserSession();
                    break;

                case PICK_WORD:
                    pickWordSession();
                    break;

                case GAMESESSION:
                    gameSession();
                    break;

                case STATS:
                    statSession();
                    break;
                default:
                    break;
            }
        }

    }

    private void statSession() {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'statSession'");
    }

    private void gameSession() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gameSession'");
    }

    private void pickWordSession() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pickWordSession'");
    }

    private void pickUserSession() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pickUserSession'");
    }

    private void menuSession() {
        DisplayManager.showMenu();
        int choice = inputHandler.enterInt(3, true, false);
        switch (choice) {
            case 1:
                this.currGameState = GameState.PICK_WORD;
                break;
            case 2:
                this.currGameState = GameState.PICK_USER;
                break;
            case 3:
                this.currGameState = GameState.STATS;
                break;
            case 0:
                this.currGameState = GameState.EXIT;
                break;

    }
}

}
