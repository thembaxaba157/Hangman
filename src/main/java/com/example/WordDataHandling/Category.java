package com.example.WordDataHandling;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;


@Data
public class Category {
    

    private String name;
    private String description;
    private HashMap<Difficulty,ArrayList<Word>> wordsByDifficulty = new HashMap<Difficulty,ArrayList<Word>>();


    public Category(String name){
        this.name = name;
        this.description = "";
    }

    
    public void addWords(Difficulty difficulty, ArrayList<Word> words){
        
        this.wordsByDifficulty.put(difficulty, words);
  
    }
    
    public Word getRandomWord(Difficulty difficulty){
        if(this.wordsByDifficulty.containsKey(difficulty)){
            return this.wordsByDifficulty.get(difficulty).get((int)(Math.random()*wordsByDifficulty.get
            (difficulty).size()));
    }
    return null;

   


}
    
    public ArrayList<Difficulty> getDifficulties(){
        return new ArrayList<>(this.wordsByDifficulty.keySet());
    }

    public ArrayList<String> getDifficultiesToStrings(){
        ArrayList<String> difficulties = new ArrayList<>();
        for(Difficulty difficulty : getDifficulties()){
            difficulties.add(difficulty.toString());
            }
            return difficulties;
    }
    
    
    public enum Difficulty{
        
        EASY,
        MEDIUM,
        HARD
    }


}
