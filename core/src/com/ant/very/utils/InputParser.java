package com.ant.very.utils;

import com.ant.very.ActionResolver;

import java.util.HashMap;

public class InputParser {
    private static final String ACTION_MOVE = "move";
    private static final String ACTION_PICKUP = "pick up";
    private static final String ACTION_BUY = "buy";
    public static final String BOT_CALL = "ring ring";

    private ActionResolver actionResolver;

    private static HashMap<String, String> responseMap;
    private static HashMap<String, String> synonymMap;

    public InputParser(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
        responseMap = new HashMap<>();
        synonymMap = new HashMap<>();
        loadWords();
    }

    private void loadWords() {
        // TODO: add variation to the responses.
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

    // When passed the sentence, finds keywords and executes methods. Returns the response string.
    //
    public String parseSentence(String sentence) {
        String response = new String(BOT_CALL);

        for (HashMap.Entry<String, String> entry : synonymMap.entrySet()) {
            String word = entry.getKey();

            if (sentence.contains(word)) {
                String cmdId = entry.getValue();

                // Execute the methods (may be moved to VRAS)
                performAction(cmdId);

                response = responseMap.get(cmdId);
            }
        }
        return response;
    }

    // TODO: figure out a way to parse and pass arguments into here.
    private void performAction(String action) {
        switch (action) {
            case ACTION_MOVE:
                actionResolver.moveAntUp();
                actionResolver.showToast("Moving the hypothetical ant", 5000);
                break;
            case ACTION_PICKUP:
                actionResolver.pickUpObject();
                actionResolver.showToast("I just picked something up in my mind!", 5000);
                break;
            case ACTION_BUY:
                actionResolver.buyItem();
                actionResolver.showToast("Thank you for this imaginary gift.", 5000);
                break;
        }
    }
}
