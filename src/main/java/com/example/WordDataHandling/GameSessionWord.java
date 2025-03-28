package com.example.WordDataHandling;

import com.example.WordDataHandling.Category.Difficulty;

import lombok.Data;

@Data
public class GameSessionWord {

    String category;
    Word word;
    Difficulty difficulty;

    public GameSessionWord(String category, Word word, Difficulty difficulty ){
        this.category = category;
        this.word = word;
        this.difficulty = difficulty;

    }


}
