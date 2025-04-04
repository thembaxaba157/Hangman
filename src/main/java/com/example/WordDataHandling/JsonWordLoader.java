package com.example.WordDataHandling;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.WordDataHandling.Category.Difficulty;

import lombok.Data;


@Data
public class JsonWordLoader {

        private String filePath;
        private ArrayList<Category> categories;
    
        public JsonWordLoader() {
            this.filePath = "words.json";
            loadCategories();
        }
    
        public JsonWordLoader(String filePath) {
            
            this.filePath = filePath;
            loadCategories();
        }


        public void loadCategories(){
            ArrayList<Category> categories = new ArrayList<>();
            JSONParser parser = new JSONParser();
            try {
                FileReader reader = new FileReader(this.filePath);
                JSONObject jsonFileObject = (JSONObject) parser.parse(reader);

                Set<String> categoriesSet =  jsonFileObject.keySet();

                for(String category : categoriesSet) {
                    Category newCategory = new Category(category);
                    JSONObject difficulties = (JSONObject) jsonFileObject.get(category);
                    Set<String> difficultiesSet = difficulties.keySet();
                    for(String difficulty : difficultiesSet){
                        if(difficulty.equals("description")){
                            
                            try {
                                newCategory.setDescription((String) difficulties.get(difficulty));
                            } catch (Exception e) {
                                System.err.println("Failed to get description from the file");
                            }
                        }
                        try {
                        
                            Difficulty difficultyConverted = Difficulty.valueOf(difficulty.toUpperCase());
                            JSONArray jsonArrayWords = (JSONArray) difficulties.get(difficulty);
                            ArrayList<Word> words = new ArrayList<>();

                            for(Object obj: jsonArrayWords){
                                JSONObject jsonObjectWord = (JSONObject) obj;
                                String word = (String) jsonObjectWord.get("word");
                                String hint = (String) jsonObjectWord.get("hint");
                                words.add(new Word(word, hint));
                            }
                            newCategory.addWords(difficultyConverted, words);
                        

                        } catch (Exception e) {
                            continue;
                        }
                        

                    }
                    
                    categories.add(newCategory);

                }

            }
            catch(Exception e){
                e.printStackTrace();
            }
            this.categories=categories;
        }

}
