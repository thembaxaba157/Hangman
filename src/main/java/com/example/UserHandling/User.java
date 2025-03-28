package com.example.UserHandling;

import lombok.Data;

@Data
public class User {

    private int id;
    private String username;
    private Score currScore = new Score();
    private int points;
    private Score highScore;


    public User(){
        
    }
    
    public User(int id, String username, int points, Score highScore){
        this.id = id;
        this.username = username;
        this.points = points;
        this.highScore = highScore;
    }

    public void addPoints(int pointsIncrease) {
        this.points += pointsIncrease;
    }

}
