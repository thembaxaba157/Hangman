package com.example;

import lombok.Data;

@Data
public class Player {
   private String username;
   private InputHandler InputHandler;


    public Player(String username, InputHandler inputHandler){
        this.username = username;
        this.InputHandler = inputHandler;
    }

}
