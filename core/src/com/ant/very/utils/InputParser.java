package com.ant.very.utils;

import com.ant.very.ActionResolver;
import static com.ant.very.utils.Constants.*;


import java.util.ArrayList;
import java.util.HashMap;

public class InputParser {
    private ActionResolver actionResolver;

    private static HashMap<String, String> responseMap;
    private static HashMap<String, String> synonymMap;
    private static ArrayList<String> moveArgs;
    private static ArrayList<String> buyArgs;

    public InputParser(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
        responseMap = new HashMap<>();
        synonymMap = new HashMap<>();
        moveArgs = new ArrayList<>();
        buyArgs = new ArrayList<>();
        loadWords();
        loadArgs();
    }

    private void loadWords() {
        // TODO: add variation to the responses. Moving should probably be silent though.
        // MOVE:
        responseMap.put(ACTION_MOVE, "Moving on.");
        synonymMap.put(ACTION_MOVE, ACTION_MOVE);
        synonymMap.put("go", ACTION_MOVE);
        synonymMap.put("walk", ACTION_MOVE);
        synonymMap.put("advance", ACTION_MOVE);
        // PICK UP:
        responseMap.put(ACTION_PICKUP, "Picked x up.");
        synonymMap.put(ACTION_PICKUP, ACTION_PICKUP);
        synonymMap.put("take", ACTION_PICKUP);
        synonymMap.put("gather", ACTION_PICKUP);
        synonymMap.put("lift", ACTION_PICKUP);
        // BUY:
        responseMap.put(ACTION_BUY, "I bought x");
        synonymMap.put(ACTION_BUY, ACTION_BUY);
        synonymMap.put("purchase", ACTION_BUY);
    }

    private void loadArgs() {
        // MOVE:
        moveArgs.add(DIRECTION_UP);
        moveArgs.add(DIRECTION_LEFT);
        moveArgs.add(DIRECTION_DOWN);
        moveArgs.add(DIRECTION_RIGHT);
        // BUY:
        buyArgs.add(ITEM_FOOD);

    }

    // When passed the sentence, finds keywords and executes methods. Returns the response string.
    public String parseSentence(String sentence) {
        String response = new String(BOT_CALL);

        for (HashMap.Entry<String, String> entry : synonymMap.entrySet()) {
            String word = entry.getKey();

            String lCaseSentence = sentence.toLowerCase();

            if (lCaseSentence.contains(word)) {
                String cmdId = entry.getValue();


                // Execute the methods (may be moved to VRAS)
                performAction(cmdId, lCaseSentence);

                response = responseMap.get(cmdId);
            }
        }
        return response;
    }

    private void performAction(String action, String sentence) {
        boolean argFound = false;
        switch (action) {
            case ACTION_MOVE:
                for (String direction : moveArgs) {
                    if (sentence.contains(direction)) {
                        argFound = true;
                        actionResolver.moveAnt(direction);
                        break;
                    }
                    }
                if (!argFound) {
                    actionResolver.showToast("Move where?", 5000);
                }
                break;
            case ACTION_PICKUP:
                // TODO: check if there's an object in the current tile.
                actionResolver.pickUpObject();
                actionResolver.showToast("I just picked something up in my mind!", 5000);
                break;
            case ACTION_BUY:
                for (String item : buyArgs) {
                    if (sentence.contains(item)) {
                        argFound = true;
                        actionResolver.buyItem(item);
                        break;
                    }
                }
                if (!argFound) {
                    actionResolver.showToast("Hmm.. what should I buy?", 5000);
                }
                break;
        }
    }
}
