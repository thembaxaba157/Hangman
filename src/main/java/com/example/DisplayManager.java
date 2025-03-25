package com.example;

import java.util.ArrayList;

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
        System.out.println("3. Deleted User");
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

}
