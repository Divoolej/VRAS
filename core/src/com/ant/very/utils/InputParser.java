package com.ant.very.utils;

import java.util.HashMap;

public class InputParser {
    private static HashMap<String, String> responseMap;
    private static HashMap<String, String> synonymMap;

    public InputParser() {
        responseMap = new HashMap<>();
        synonymMap = new HashMap<>();
        loadWords();
    }

    private void loadWords() {
        // MOVE:
        responseMap.put("move", "Moving on.");
        // Load the synonyms:
        synonymMap.put("move", "move");
        synonymMap.put("go", "move");
        synonymMap.put("walk", "move");
        synonymMap.put("advance", "move");
    }
}
