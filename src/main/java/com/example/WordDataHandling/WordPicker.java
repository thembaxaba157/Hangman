package com.example.WordDataHandling;

import java.util.ArrayList;

import com.example.InputHandler;

public class WordPicker {
    
    private WordManager wordManager;
    private InputHandler inputHandler;

    public WordPicker(WordManager wordManager, InputHandler inputHandler) {
        this.wordManager = wordManager;
        this.inputHandler = inputHandler;
    }


    private Word pickWord(){
        Category pickedCategory = pickCategory();
        return null;
        
    }
        
    private Category pickCategory() {
        ArrayList<String> categories = wordManager.getCategoryNames();
        int categoryNum = inputHandler.enterInt(categories.size(), true, true);
        try {
            
        } catch (Exception e) {
            return new Category(null, null);
        }

        return new Category(null, null);
    }

}
