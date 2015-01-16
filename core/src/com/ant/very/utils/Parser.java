package com.ant.very.utils;

import com.ant.very.ActionResolver;
import com.ant.very.objects.Ant;

import static com.ant.very.utils.Constants.*;


import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private ActionResolver actionResolver;

    private static HashMap<String, String> responseMap;
    private static HashMap<String, String> synonymMap;
    private static ArrayList<String> moveArgs;
    private static ArrayList<String> buyArgs;
    private final ArrayList<String> quantityArgs;

    public Parser(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
        responseMap = new HashMap<>();
        synonymMap = new HashMap<>();
        moveArgs = new ArrayList<>();
        buyArgs = new ArrayList<>();
        quantityArgs = new ArrayList<>();
        loadWords();
        loadArgs();
    }

    private void loadWords() {
        // TODO: add variation to the responses. Moving should probably be silent though.
        // MOVE:
        responseMap.put(ACTION_MOVE, "Moving on.");
        synonymMap.put(ACTION_MOVE, ACTION_MOVE);
        synonymMap.put(" go ", ACTION_MOVE);
        synonymMap.put(" walk ", ACTION_MOVE);
        synonymMap.put(" advance ", ACTION_MOVE);
        // PICK UP:
        responseMap.put(ACTION_PICKUP, "Picked x up.");
        synonymMap.put(ACTION_PICKUP, ACTION_PICKUP);
        synonymMap.put(" take ", ACTION_PICKUP);
        synonymMap.put(" gather ", ACTION_PICKUP);
        synonymMap.put(" lift ", ACTION_PICKUP);
        // BUY:
        responseMap.put(ACTION_BUY, "I bought x");
        synonymMap.put(ACTION_BUY, ACTION_BUY);
        synonymMap.put(" purchase ", ACTION_BUY);
        // SELL:
        responseMap.put(ACTION_SELL, "I sold x");
        synonymMap.put(ACTION_SELL, ACTION_SELL);
        synonymMap.put(" trade ", ACTION_SELL);
        //DIG:
        responseMap.put(ACTION_DIG, "Diggy doo");
        synonymMap.put(ACTION_DIG, ACTION_DIG);
        // QUANTITY:
        responseMap.put(ACTION_QUANTITY, "I've got x x");
        synonymMap.put(ACTION_QUANTITY, ACTION_QUANTITY);
        synonymMap.put(" how much ", ACTION_QUANTITY);
    }

    private void loadArgs() {
        // MOVE:
        moveArgs.add(DIRECTION_UP);
        moveArgs.add(DIRECTION_LEFT);
        moveArgs.add(DIRECTION_DOWN);
        moveArgs.add(DIRECTION_RIGHT);
        // BUY:
        buyArgs.add(ITEM_CHERRY);
        buyArgs.add(ITEM_FUEL);
        buyArgs.add(ITEM_BIGGER_BACKPACK);
        // QUANTITY:
        quantityArgs.add(ITEM_CHERRY);
        quantityArgs.add(ITEM_FUEL);
        quantityArgs.add(ITEM_FREE_SPACE);
    }

    // When passed the sentence, finds keywords and executes methods. Returns the response string.
    public String parseSentence(String sentence) {
        String response = new String(BOT_CALL);

        for (HashMap.Entry<String, String> entry : synonymMap.entrySet()) {
            String word = entry.getKey();

            String lCaseSentence = sentence.toLowerCase();

            if (lCaseSentence.contains(word)) {
                String cmdId = entry.getValue();

//                Execute the methods
                response = actAndRespond(cmdId, lCaseSentence);

                if (response.equals("")) {
                    response = responseMap.get(cmdId);
                }
            }
        }
        return response;
    }

    private String actAndRespond(String action, String sentence) {
        boolean argFound = false;
        switch (action) {
            case ACTION_MOVE:
                for (String direction : moveArgs) {
                    if (sentence.contains(direction)) {
                        argFound = true;
                        Ant.getInstance().moveInDirection(direction);
                        break;
                    }
                }
                if (!argFound) {
                    return "Move in which direction?";
                }
                break;
            case ACTION_DIG:
                for (String direction : moveArgs) {
                    if (sentence.contains(direction)) {
                        argFound = true;
                        Ant.getInstance().digInDirection(direction);
                        break;
                    }
                }
                if (!argFound) {
                    return "Dig in which direction?";
                }
                break;
            case ACTION_PICKUP:
                // TODO: check if there's an object in the current tile.
                actionResolver.pickUpObject();
                return  "I just picked something up in my mind!";
            case ACTION_BUY:
                for (String item : buyArgs) {
                    if (sentence.contains(item)) {
                        argFound = true;
//                        Shop.buyItem(item);
                        // TODO: Wat do do if ant has no monies.
                        return "I bought " + item + ".";
                    }
                }
                if (!argFound) {
                    return "Hmm.. what do you want me to buy?";
                }
            case ACTION_QUANTITY:
                for (String item : quantityArgs) {
                    if (sentence.contains(item)) {
                        String foundItem = item;
                        argFound = true;
                        String quantity = Ant.getInstance().getQuantity(foundItem);
                        return "I've got " + quantity;
                    }
                }
        }
        return "";
    }
}
