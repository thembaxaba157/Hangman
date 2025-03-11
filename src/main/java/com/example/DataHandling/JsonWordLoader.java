package com.example.DataHandling;
import org.json.simple.*;

import lombok.Data; 


@Data
public class JsonWordLoader {

        private String filePath;
    
        public JsonWordLoader() {
            this.filePath = "words.json";
        }
    
        public JsonWordLoader(String filePath) {
            
            this.filePath = filePath;
        
        }

        


}
