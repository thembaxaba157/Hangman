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

    public ArrayList<Category> getCategories(){
        return jsonWordLoader.getCategories();
    }

    public ArrayList<String> getCategoryNames() {
       
        ArrayList<String> categories = new ArrayList<>();
        for(Category category : getCategories()){
            categories.add(category.getName());
            }
            return categories;
    }
    
    


}
