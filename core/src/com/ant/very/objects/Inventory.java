package com.ant.very.objects;

import com.ant.very.utils.Constants;

public class Inventory {
    private int currentSize;
    private int fuel;
    private int maxFuel = 100;
    private int maxSize = 10;
    private int pickTier = 0; //Variable to determine which blocks are possible to break

    public int getNumCherries() {
        return numCherries;
    }
    public int getNumBerries() {
        return numBerries;
    }

    private int numCherries;
    private int numBerries;
    private int numRaspberries;
    private int numBlueberries;

    private int money;

    public Inventory(int size) {
        this.currentSize = size;
        fuel = maxFuel;
        money = 0;
    }

    //TODO: Trebuszq - link this to some command like "show me your itemz, you little beyotch"
    public String getContent() {
        String result = "I have ";
        if (numCherries > 0)
            result += Integer.toString(numCherries) + " cherries, ";
        if (numBerries > 0)
            result += Integer.toString(numBerries) + " berries, ";
        if (numRaspberries > 0)
            result += Integer.toString(numRaspberries) + " raspberries, ";
        if (numBlueberries > 0)
            result += Integer.toString(numBlueberries) + " blueberries, ";
        if (result == "I have ")
            result += "nothing";
        return result;
    }

    public void addCherry() {
        if (maxSize > currentSize) {
            numCherries++;
            currentSize++;
        }
    }

    public void setMaxSize(int size) {
        this.maxSize = size;
    }

    public void removeCherry(int amount) {
        if (numCherries - amount >= 0) {
            numCherries -= amount;
        }
    }

    public void removeBerry(int amount) {
        if (numBerries - amount >= 0) {
            numBerries -= amount;
        }
    }

    public void removeRaspberry(int amount) {
        if (numRaspberries - amount >= 0) {
            numRaspberries -= amount;
        }
    }

    public void removeBlueberry(int amount) {
        if (numBlueberries - amount >= 0) {
            numBlueberries -= amount;
        }
    }

    public int getCurrentFuel() {
        return fuel;
    }

    public void burnFuel(int howMuch) {
        fuel -= howMuch;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public int getFreeSpace() {
        return maxSize - currentSize;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void removeMoney(int amount) {
        if (money - amount < 0) {
            money = 0;
        } else {
            money -= amount;
        }
    }

    public void refillFuel() {
        fuel = 100;
    }

    public int getMoney() {
        return money;
    }

    public int getPickTier() {
        return pickTier;
    }

    public void upgradePickTier() { if (pickTier < Constants.TIER_MARBLE) pickTier++; }

    public void addBerry() {
        if (maxSize > currentSize) {
            numBerries++;
            currentSize++;
        }
    }

    public void addRaspberry() {
        if (maxSize > currentSize) {
            numRaspberries++;
            currentSize++;
        }
    }

    public void addBlueberry() {
        if (maxSize > currentSize) {
            numBlueberries++;
            currentSize++;
        }
    }

    public int getNumRaspberries() {
        return numRaspberries;
    }

    public int getNumBlueberries() {
        return numBlueberries;
    }
}
