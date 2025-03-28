package com.example.UserHandling;

import java.util.ArrayList;

import com.example.DisplayManager;
import com.example.InputHandler;
import com.example.DatabaseHandling.DatabaseManager;
import lombok.*;


public class UserSession {
    private User user = null;
    private DatabaseManager databaseManager;
    private InputHandler inputHandler;

    public UserSession(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.databaseManager = new DatabaseManager();
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public UserSession(InputHandler inputHandler, User user) {
        this.inputHandler = inputHandler;
        this.user = user;
        this.databaseManager = new DatabaseManager();
    }

    public boolean createUserSession() {
        while (true) {
            String username = inputHandler.getAlphaNumString();
            if (username.equals("-1")) {
                return false;
            } else if (this.databaseManager.addPlayer(username)) {
                this.user = this.databaseManager.loadPlayer(username);
                if (this.user != null) {
                    break;
                }
            }

        }

        return true;

    }

    public boolean loadUserSession() {
        ArrayList<String> players = databaseManager.getPlayers();
        if (players.size() == 0) {
            System.out.println("No players exist please a create one");
            return false;
        }

        while (true) {
            DisplayManager.showOptions(players, true, true, "Select a player to load:");
            int num = inputHandler.enterInt(players.size(), true, true);
            if (num == 0) {
                break;
            }
            if (num == 99) {
                return false;
            }
            if (num > 0 && num <= players.size()) {
                this.user = this.databaseManager.loadPlayer(players.get(num - 1));
                if (!this.user.equals(null)) {
                    break;
                    }
                    }
    

       
    }
    return true;
}

    public boolean deleteUserSession() {
        ArrayList<String> players = databaseManager.getPlayers();
        if (players.size() == 0) {
            System.out.println("No players available for deletion");
            return false;
            }
            while (true) {
                DisplayManager.showOptions(players, true, true, "Select a player to delete:");
                int num = inputHandler.enterInt(players.size(), true, true);
                if (num == 0) {
                    return false;
                    }
                    if (num == 99) {
                        break;
                        }
                        if (num > 0 && num <= players.size()) {
                            this.databaseManager.deletePlayer(players.get(num - 1));
                            System.out.println("Player deleted");

                            if (this.user.getUsername().equals(players.get(num - 1))){
                                this.user = null;
                            }
                            
                            return true;
                            }
                            }
        
        return true;

    }

    public boolean viewCurrUser() {
        
        if (this.user == null) {
            System.out.println("\nNo user is currently logged in");
            }
        else {
            DisplayManager.showUser(this.user);
        }
        DisplayManager.showOptions(new ArrayList<String>(), true, true, "");
        System.out.println("\n");
        int optionNum = inputHandler.enterInt(0, true, true);
        if(optionNum == 0){
            return true;
        }
        return false;
    }

    public void updateScore() {
        this.databaseManager.addScoreEntry(this.user);
    }

    public void resetScore() {
        this.user.setCurrScore(new Score());
    }

    public void updatePoints() {
        this.databaseManager.updatePoints(this.user);
    }

}
