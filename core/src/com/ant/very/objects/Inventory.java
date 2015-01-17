package com.ant.very.objects;

public class Inventory {
    private int currentSize;
    private int fuel;
    private int maxFuel = 100;
    private int maxSize = 10;

    public int getNumCherries() {
        return numCherries;
    }

    private int numCherries;
    private int money;

    public Inventory(int size) {
        this.currentSize = size;
        fuel = maxFuel;
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
}
