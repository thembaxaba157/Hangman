package com.example;

import com.example.State.GameState;
import com.example.UserHandling.UserSession;
import com.example.WordDataHandling.WordManager;

public class Hangman {
    private GameState currGameState;
    private WordManager wordManager;
    private InputHandler inputHandler;
    private UserSession userSession;


    public Hangman() {
        this.inputHandler = new InputHandler();
        this.wordManager = new WordManager();
        this.userSession = new UserSession(this.inputHandler);

        this.currGameState = GameState.MENU;
            

    }

    public void run() {
        while (this.currGameState != GameState.EXIT) {
            switch (this.currGameState) {
                case MENU:
                    menuSession();
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
        System.out.println("Goodbye!");

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
        DisplayManager.showUserMenu();
        boolean success = false;
        int choise = inputHandler.enterInt(4, true, false);
        switch (choise) {
            case 0:
                this.currGameState = GameState.MENU;
                break;
            
            // createUser
            case 1:
               success = this.userSession.createUserSession();
               if(success){
                this.currGameState = GameState.MENU;
               }
               break;
            
            case 2:
               success = this.userSession.loadUserSession();
               if(success){
                this.currGameState = GameState.MENU;
                }
                break;
            
            case 3:
            success = this.userSession.deleteUserSession();
            if(success){
                this.currGameState = GameState.MENU;
                }
                break;
            
            case 4:
                success = this.userSession.viewCurrUser();
                if(success){
                    this.currGameState = GameState.MENU;
                    }
                    break;

    }
            
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
