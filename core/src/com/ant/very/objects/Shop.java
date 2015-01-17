package com.ant.very.objects;

/**
 * Created by divoolej on 16.01.15.
 */
public class Shop {
    public boolean isAvailable() { //Ant must stand in the squares around the shop
        int absX = Ant.getInstance().getX() - 9;
        int absY = Ant.getInstance().getY() - 4;
        if (absX >= -1 || absX <= 1) {
            if (absY >= -1 || absY <= 1) {
                return true;
            }
        }
        return false;
    }

    public boolean sellCherry(int amount) {
        if (Ant.getInstance().getEq().getNumCherries() >= amount) {
            Ant.getInstance().getEq().addMoney(amount * 5);
            Ant.getInstance().getEq().removeCherry(amount);
            return true;
        }
        return false;
    }

    public boolean buyFuel() {
        if (Ant.getInstance().getEq().getMoney() >= 20) {
            Ant.getInstance().getEq().removeMoney(20);
            Ant.getInstance().getEq().refillFuel();
            return true;
        }
        return false;
    }
}
