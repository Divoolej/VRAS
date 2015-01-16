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

    public void removeCherry() {
        if (numCherries > 0) {
            numCherries--;
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
}
