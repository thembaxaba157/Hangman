
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.WordDataHandling.Category;
import com.example.WordDataHandling.JsonWordLoader;
import com.example.WordDataHandling.Word;

class JsonWordLoaderTest {

    private static final String TEST_JSON_FILE = "test_words.json";

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary test JSON file
        String sampleJson = """
            {
              "Movies": {
                "description": "Famous movies around the world",
                "easy": [
                  {"word": "Avatar", "hint": "James Cameron sci-fi film"},
                  {"word": "Titanic", "hint": "Ship disaster love story"}
                ],
                "medium": [
                  {"word": "Inception", "hint": "Dream within a dream"}
                ],
                "hard": [
                  {"word": "Memento", "hint": "Man with short-term memory loss"}
                ]
              },
              "FootballClubs": {
                "description": "Popular football clubs",
                "easy": [
                  {"word": "Chelsea", "hint": "Blue London club"},
                  {"word": "RealMadrid", "hint": "Spanish club with many UCLs"}
                ]
              }
            }
            """;

        try (FileWriter fileWriter = new FileWriter(TEST_JSON_FILE)) {
            fileWriter.write(sampleJson);
        }
    }

    @AfterEach
    void tearDown() {
        // Delete the temporary test file
        java.io.File file = new java.io.File(TEST_JSON_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    
    @Test
    void testLoadCategoriesSuccessfully() {
        JsonWordLoader loader = new JsonWordLoader(TEST_JSON_FILE);

        assertNotNull(loader.getCategories(), "Categories list should not be null");
        assertEquals(2, loader.getCategories().size(), "Should load 2 categories");

        Category moviesCategory = loader.getCategories().stream()
                .filter(c -> c.getName().equals("Movies"))
                .findFirst()
                .orElse(null);

        assertNotNull(moviesCategory, "Movies category should exist");
        assertEquals("Famous movies around the world", moviesCategory.getDescription());

        // Check difficulties
        ArrayList<Category.Difficulty> difficulties = moviesCategory.getDifficulties();
        assertTrue(difficulties.contains(Category.Difficulty.EASY));
        assertTrue(difficulties.contains(Category.Difficulty.MEDIUM));
        assertTrue(difficulties.contains(Category.Difficulty.HARD));

        // Check a word
        ArrayList<Word> easyWords = moviesCategory.getWordsByDifficulty().get(Category.Difficulty.EASY);
        assertNotNull(easyWords);
        assertEquals(2, easyWords.size());
        assertEquals("Avatar", easyWords.get(0).getName());
        assertEquals("James Cameron sci-fi film", easyWords.get(0).getHint());
    }

    @Test
    void testCategoryWithoutSomeDifficulties() {
        JsonWordLoader loader = new JsonWordLoader(TEST_JSON_FILE);

        Category footballCategory = loader.getCategories().stream()
                .filter(c -> c.getName().equals("FootballClubs"))
                .findFirst()
                .orElse(null);

        assertNotNull(footballCategory, "FootballClubs category should exist");

        // Only "easy" difficulty should exist
        ArrayList<Category.Difficulty> difficulties = footballCategory.getDifficulties();
        assertEquals(1, difficulties.size());
        assertTrue(difficulties.contains(Category.Difficulty.EASY));

        ArrayList<Word> easyWords = footballCategory.getWordsByDifficulty().get(Category.Difficulty.EASY);
        assertEquals(2, easyWords.size());
        assertEquals("Chelsea", easyWords.get(0).getName());
        assertEquals("Blue London club", easyWords.get(0).getHint());
    }

    @Test
    void testInvalidFilePath() {
        JsonWordLoader loader = new JsonWordLoader("non_existing_file.json");

        // Should catch exception internally, but categories list should not be null
        assertNotNull(loader.getCategories());
        assertEquals(0, loader.getCategories().size(), "No categories should be loaded for invalid file");
    }
}
