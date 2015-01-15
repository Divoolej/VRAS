package com.ant.very.objects;

/**
 * Created by divoolej on 15.01.15.
 */
public class Equipment {
    int size;
    int max_size;
    int num_cherries;

    public Equipment(int size) {
        this.size = size;
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

    public void removeCherry() {
        if (num_cherries > 0) {
            num_cherries--;
        }
    }

    public int getSize() {
        return size;
    }

    public int getFreeSpace() {
        return max_size - sizge;
    }
}
