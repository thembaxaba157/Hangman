package com.example.WordDataHandling;

import java.util.ArrayList;

import com.example.DisplayManager;
import com.example.InputHandler;
import com.example.WordDataHandling.Category.Difficulty;

import lombok.Data;

@Data
public class WordManager {

    private JsonWordLoader jsonWordLoader;
    private InputHandler inputHandler;
    private GameSessionWord gameSessionWord = null;


    public WordManager(InputHandler inputHandler){
        this.jsonWordLoader = new JsonWordLoader();
        this.inputHandler = inputHandler;
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

    public void displayAvailableCategories(){
        DisplayManager.display(getCategoryNames());

    }

    public boolean pickWordSession() {
        ArrayList<Category> availableCategories = getCategories();
        while(true){
        DisplayManager.showOptions(getCategoryNames(), true, false, "Select Category:");
        int selectedCategoryIndex = inputHandler.enterInt(availableCategories.size(), true, false);
        if(selectedCategoryIndex==99){
            return false;
        }
        Category selectedCategory = availableCategories.get(selectedCategoryIndex-1);
        ArrayList<String> availableDifficulties = selectedCategory.getDifficultiesToStrings();
        int diffucultyPosition = pickDifficultySession(availableDifficulties);
        if(diffucultyPosition == 99){
            return false;
            }
        if(diffucultyPosition == 0){
            continue;
        }

        try {
            Difficulty difficulty = Difficulty.valueOf(availableDifficulties.get(diffucultyPosition-1).toUpperCase());
            pickRandomWord(selectedCategory, difficulty);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid difficulty");
        }
        
        return false;

                }
                
            }

        public void pickRandomWord(Category selectedCategory, Difficulty difficulty){
            Word pickedWord = selectedCategory.getRandomWord(difficulty);
            pickedWord.setName(pickedWord.getName().toLowerCase());
            this.gameSessionWord = new GameSessionWord(selectedCategory.getName(), pickedWord, difficulty);
        }
        
            private int pickDifficultySession(ArrayList<String> availableDifficulties) {
                
                    DisplayManager.showOptions(availableDifficulties, true, true, "Select Difficulty:");
                    return inputHandler.enterInt(availableDifficulties.size(), true, true);
            }

            public void pickWordAgain() {
                Category selectedCategory = null;
                for(Category category: getCategories()){
                    if(category.getName().equals(this.gameSessionWord.getCategory())){
                        selectedCategory = category;
                        break;
                    }
                }
                pickRandomWord(selectedCategory, gameSessionWord.getDifficulty());
            }
    
}
