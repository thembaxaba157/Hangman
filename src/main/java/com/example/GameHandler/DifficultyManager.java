package com.example.GameHandler;

import java.util.HashMap;
import java.util.Map;

import com.example.WordDataHandling.Category.Difficulty;

public class DifficultyManager {
    private static final Map<Difficulty, Integer> difficultyLevels = new HashMap<>();

    static {
        difficultyLevels.put(Difficulty.EASY, 1);
        difficultyLevels.put(Difficulty.MEDIUM, 2);
        difficultyLevels.put(Difficulty.HARD, 3);
    }

    public static int getDifficultyMultiplier(Difficulty difficulty) {
        return difficultyLevels.getOrDefault(difficulty, 1);
    }
}