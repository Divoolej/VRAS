package com.ant.very.utils;

import com.ant.very.ActionResolver;
import com.ant.very.objects.Ant;
import com.ant.very.objects.Shop;
import com.badlogic.gdx.Gdx;

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
        responseMap.put(ACTION_SHOW_ALL_ITEMS, "I've got loads of stuff!");
        synonymMap.put(ACTION_SHOW_ALL_ITEMS, ACTION_SHOW_ALL_ITEMS);
        synonymMap.put(" show all items", ACTION_SHOW_ALL_ITEMS);
        synonymMap.put(" show me all your items", ACTION_SHOW_ALL_ITEMS);
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
        quantityArgs.add(ITEM_BLUEBERRY);
        quantityArgs.add(ITEM_RASPBERRY);
        quantityArgs.add(ITEM_BERRY);
        quantityArgs.add(ITEM_FUEL);
        quantityArgs.add(ITEM_FREE_SPACE);
    }

    // To add a new action:


    // When passed the sentence, finds keywords and executes methods. Returns the response string.
    public String parseSentence(String sentence) {
        String response = new String(BOT_CALL);

        // Look for keyword entries in the synonym map:
        for (HashMap.Entry<String, String> entry : synonymMap.entrySet()) {
            String word = entry.getKey();

            String lCaseSentence = sentence.toLowerCase();

            if (lCaseSentence.contains(word)) {
                String cmdId = entry.getValue();

//                Execute the methods
                response = actAndRespond(cmdId, lCaseSentence);

//                If the parser is clueless, return a standard response:
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
                        return Ant.getInstance().digInDirection(direction);
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
            case ACTION_SHOW_ALL_ITEMS:
                Gdx.app.log("Parser", "show items");
                return Ant.getInstance().getEq().getContent();
            case ACTION_BUY:
                for (String item : buyArgs) {
                    if (sentence.contains(item)) {
                        argFound = true;
//                       Shop.buyItem(item);
                        // TODO: Wat do if ant has no monies.
                        return "I bought " + item + ".";
                    }
                }
                if (!argFound) {
                    return "Hmm.. what do you want me to buy?";
                }
            case ACTION_SHOW_QUANTITY:
                    for (String item : quantityArgs) {
                    if (sentence.contains(item)) {
                        return Ant.getInstance().getQuantity(item);
                    }
                    else return "I've got none of that.";
                }

        }
        return "";
    }
}
