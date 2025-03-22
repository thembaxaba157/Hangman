package com.example.WordDataHandling;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Data;


@Data
public class Category {
    

    private String name;
    private String description;
    private HashMap<Difficulty,ArrayList<Word>> wordsByDifficulty;


    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }

    
    public void addWords(Difficulty difficulty, ArrayList<Word> words){
        
        this.wordsByDifficulty.put(difficulty, words);
  
    }
    
    public Word getRandomWord(Difficulty difficulty){
        if(wordsByDifficulty.containsKey(difficulty)){
            return wordsByDifficulty.get(difficulty).get((int)(Math.random()*wordsByDifficulty.get
            (difficulty).size()));
    }
    return null;

   


}
    
    public ArrayList<Difficulty> getDifficulties(){
        return new ArrayList<>(wordsByDifficulty.keySet());
    }
    
    
    public enum Difficulty{
        
        EASY,
        MEDIUM,
        HARD
    }

}
