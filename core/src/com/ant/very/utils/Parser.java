package com.ant.very.utils;

import com.ant.very.ActionResolver;
import com.ant.very.objects.Ant;
import com.ant.very.objects.Shop;
import com.ant.very.objects.map.Blueberry;
import com.badlogic.gdx.Gdx;

import static com.ant.very.utils.Constants.*;


import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private ActionResolver actionResolver;

    private static HashMap<String, String> responseMap;
    private static HashMap<String, String> synonymMap;
    private static ArrayList<String> directionArgs;
    private static ArrayList<String> buyArgs;
    private final ArrayList<String> quantityArgs;
    private final ArrayList<String> sellArgs;

    public Parser(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
        responseMap = new HashMap<>();
        synonymMap = new HashMap<>();
        directionArgs = new ArrayList<>();
        buyArgs = new ArrayList<>();
        sellArgs = new ArrayList<>();
        quantityArgs = new ArrayList<>();
        loadWords();
        loadArgs();
    }

    private void loadWords() {
        // MOVE:
        responseMap.put(ACTION_MOVE, "Move in which direction?");
        synonymMap.put(ACTION_MOVE, ACTION_MOVE);
        synonymMap.put(" go ", ACTION_MOVE);
        synonymMap.put(" walk ", ACTION_MOVE);
        // PICK UP:
        responseMap.put(ACTION_PICKUP, "I picked nothing up.");
        synonymMap.put(ACTION_PICKUP, ACTION_PICKUP);
        synonymMap.put(" pick up", ACTION_PICKUP);
        synonymMap.put(" take", ACTION_PICKUP);
        synonymMap.put(" gather", ACTION_PICKUP);
        synonymMap.put(" lift", ACTION_PICKUP);
        // BUY:
        responseMap.put(ACTION_BUY, "I bought nothing");
        synonymMap.put(ACTION_BUY, ACTION_BUY);
        synonymMap.put(" purchase ", ACTION_BUY);
        synonymMap.put(" upgrade ", ACTION_BUY);
        synonymMap.put(" refill ", ACTION_BUY);
        // SELL:
        responseMap.put(ACTION_SELL, "I can't sell that.");
        synonymMap.put(ACTION_SELL, ACTION_SELL);
        synonymMap.put(" trade ", ACTION_SELL);
        //DIG:
        responseMap.put(ACTION_DIG, "Diggy doo");
        synonymMap.put(ACTION_DIG, ACTION_DIG);
        // SHOW_ALL_ITEMS:
        responseMap.put(ACTION_SHOW_ALL_ITEMS, "I've got loads of stuff!");
        synonymMap.put(ACTION_SHOW_ALL_ITEMS, ACTION_SHOW_ALL_ITEMS);
        synonymMap.put(" show all items", ACTION_SHOW_ALL_ITEMS);
        synonymMap.put(" show me all your items", ACTION_SHOW_ALL_ITEMS);
        // SHOW QUANTITY:
        responseMap.put(ACTION_SHOW_QUANTITY, "I've got none of that.");
        synonymMap.put(ACTION_SHOW_QUANTITY, ACTION_SHOW_QUANTITY);
        synonymMap.put(" how much ", ACTION_SHOW_QUANTITY);
        synonymMap.put(" do you have any ", ACTION_SHOW_QUANTITY);
        // LOOK:
        responseMap.put(ACTION_LOOK, "I'm looking great.");
        synonymMap.put(ACTION_LOOK, ACTION_LOOK);
        synonymMap.put(" look ", ACTION_LOOK);
    }

    private void loadArgs() {
        // MOVE:
        directionArgs.add(DIRECTION_UP);
        directionArgs.add(DIRECTION_LEFT);
        directionArgs.add(DIRECTION_DOWN);
        directionArgs.add(DIRECTION_RIGHT);
        directionArgs.add(LOCATION_HOME);
        // BUY:
        buyArgs.add(ITEM_FUEL);
        buyArgs.add(ITEM_PICK_UPGRADE);
//        buyArgs.add(ITEM_BIGGER_BACKPACK);
        // SELL:
        sellArgs.add(ITEM_BERRY);
        sellArgs.add(ITEM_CHERRY);
        sellArgs.add(ITEM_BLUEBERRY);
        sellArgs.add(ITEM_RASPBERRY);
        // QUANTITY:
        quantityArgs.add(ITEM_MONEY);
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
                for (String direction : directionArgs) {
                    if (sentence.contains(direction)) {
                        return Ant.getInstance().moveInDirection(direction);
                    }
                }
                break;
            case ACTION_DIG:
                for (String direction : directionArgs) {
                    if (sentence.contains(direction)) {
                        return Ant.getInstance().digInDirection(direction);
                    }
                }
                break;
            case ACTION_LOOK:
                for (String direction : directionArgs) {
                    if (sentence.contains(direction)) {
                        return Ant.getInstance().lookInDirection(direction);
                    }
                }
                break;
            case ACTION_PICKUP:
                return Ant.getInstance().pickUp();
            case ACTION_SHOW_ALL_ITEMS:
                Gdx.app.log("Parser", "show items");
                return Ant.getInstance().getEq().getContent();
            case ACTION_BUY:
                for (String item : buyArgs) {
                    if (sentence.contains(item)) {
                        if (Shop.isAvailable()) {
                            Gdx.app.log("shop", "close to shop");
                            switch (item) {
                                case ITEM_FUEL:
                                    return Shop.buyFuel();
                                case ITEM_PICK_UPGRADE:
                                    return Shop.upgradePick();
                            }
                        }
                        else return "I have to stand closer to the ant shop.";
                    }
                }
                break;
            case ACTION_SELL:
                for (String item : sellArgs) {
                    if (sentence.contains(item)) {
                        if (Shop.isAvailable()) {
                            switch (item) {
                                case ITEM_BERRY:
                                    return Shop.sellBerry(1);
                                case ITEM_BLUEBERRY:
                                    return Shop.sellBlueberry(1);
                                case ITEM_CHERRY:
                                    return Shop.sellCherry(1);
                                case ITEM_RASPBERRY:
                                    return Shop.sellRaspberry(1);
                            }
                        }
                        else return "I have to stand closer to the ant shop.";
                    }
                }
                break;
            case ACTION_SHOW_QUANTITY:
                    for (String item : quantityArgs) {
                    if (sentence.contains(item)) {
                        return Ant.getInstance().getQuantity(item);
                    }
                }
                break;
        }
        return "";
    }
}
