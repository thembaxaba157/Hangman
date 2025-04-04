package com.example;

import com.example.State.GameState;
import com.example.GameHandler.GameSession;
import com.example.UserHandling.User;
import com.example.UserHandling.UserSession;
import com.example.WordDataHandling.WordManager;

public class Hangman {
    private GameState currGameState;
    private WordManager wordManager;
    private InputHandler inputHandler;
    private UserSession userSession;


    public Hangman() {
        this.inputHandler = new InputHandler();
        this.wordManager = new WordManager(this.inputHandler);
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

                case PLAY_MENU:
                    PlayMenuSession();
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

       userSession.viewTopFiveleaderboard();
    }

    private void gameSession() {

        GameSession gameSession = new GameSession(inputHandler, wordManager.getGameSessionWord(), userSession.getUser());
        boolean success = gameSession.play();
        User gameSessionUser = gameSession.getUser();
        userSession.setUser(gameSessionUser);
        
        if (success) {
            DisplayManager.continuePlay();
            int n = inputHandler.enterInt(2, false, false);


            if (n == 2) {
            userSession.updateScore();
            userSession.resetScore();
            this.currGameState = GameState.PLAY_MENU;
            }
            else if (n == 1){
                wordManager.pickWordAgain();
            }

        }

        else {
            this.currGameState = GameState.PLAY_MENU;
        }

        userSession.updatePoints();
        
        
    }

    private void PlayMenuSession() {
      DisplayManager.showPlayMenu();
      int choice = inputHandler.enterInt(3, true, false);
      boolean sucess = false;
      switch (choice) {
        case 0:
        this.currGameState = GameState.MENU;
        break;
        
        case 1:
            if(userSession.getUser()==null){
                System.out.println("You must select a user to play.");
                break;
            }
            sucess = wordManager.pickWordSession();
            if(sucess){
                currGameState = GameState.GAMESESSION;
            }
           
            break;
        
        case 2:
            wordManager.displayAvailableCategories();
            inputHandler.waitForAnyKey();
            break;
            
        case 3:
            DisplayManager.showRules();
        
      }

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
                this.currGameState = GameState.PLAY_MENU;
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
