package com.ant.very.objects;

/**
 * Created by divoolej on 15.01.15.
 */
public class Equipment {
    int size;
    int max_size;
    int num_cherries;
    int money;
    int fuel;

    public Equipment(int size) {
        this.max_size = size;
        this.money = 0;
        this.size = 0;
        this.num_cherries = 0;
        this.fuel = 100;
    }

    public int getFuel() {
        return fuel;
    }

    public void refillFuel() {
        fuel = 100;
    }

    public int getMoney() {
        return money;
    }

    public void eatFuel(int amount) {
        if (fuel - amount >= 0) {
            fuel -= amount;
        }
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

    public void addCherry() {
        if (max_size > size) {
            num_cherries++;
            size++;
        }
    }

    public void changeSize(int size) {
        this.max_size = size;
    }

    public void removeCherry(int amount) {
        if (num_cherries - amount >= 0) {
            num_cherries -= amount;
        }
    }

    public int getSize() {
        return size;
    }

    public int getFreeSpace() {
        return max_size - size;
    }

    public int getNumCherries() {
        return num_cherries;
    }
}
