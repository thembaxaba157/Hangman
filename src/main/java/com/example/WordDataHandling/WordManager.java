package com.example.WordDataHandling;

import java.util.ArrayList;

import com.example.WordDataHandling.Category.Difficulty;

import lombok.Data;

@Data
public class WordManager {

    private JsonWordLoader jsonWordLoader;


    public WordManager(){
        this.jsonWordLoader = new JsonWordLoader();
    }


    public void displayCategories(){
        for (Category category: jsonWordLoader.getCategories()){
            System.out.println(category.getName());
            System.out.println(category.getDescription());
        }
    }

    public void displayLevels(ArrayList<Difficulty> difficulties){
        for (int i=0;i<difficulties.size();++i){
            System.out.println((i+1)+"."+difficulties.get(i).toString());
        }

    }

    public ArrayList<Category> getCategories(){
        return jsonWordLoader.getCategories();
    }
    
    


}
