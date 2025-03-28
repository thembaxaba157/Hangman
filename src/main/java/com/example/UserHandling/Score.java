package com.example.UserHandling;

import java.sql.Date;

import lombok.Data;

@Data
public class Score {

    private int scoreValue;
    private Date date;
    

    public Score(){
        this.date = new Date(System.currentTimeMillis());
        this.scoreValue = 0;
    }

    public Score(int scoreValue, Date date){
        this.scoreValue = scoreValue;
        this.date = date;
    }

    public void addScore(int scoreIncrease, Date date2) {
        this.scoreValue += scoreIncrease;
        this.date = date2;
       
    }
    
}
