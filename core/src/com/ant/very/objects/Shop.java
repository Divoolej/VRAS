package com.ant.very.objects;

import com.ant.very.utils.Constants;

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

    public boolean sellBerry(int amount) {
        if (Ant.getInstance().getEq().getNumBerries() >= amount) {
            Ant.getInstance().getEq().addMoney(amount * 7);
            Ant.getInstance().getEq().removeBerry(amount);
            return true;
        }
        return false;
    }

    public boolean sellRaspberry(int amount) {
        if (Ant.getInstance().getEq().getNumRaspberries() >= amount) {
            Ant.getInstance().getEq().addMoney(amount * 10);
            Ant.getInstance().getEq().removeRaspberry(amount);
            return true;
        }
        return false;
    }

    public boolean sellBlueberry(int amount) {
        if (Ant.getInstance().getEq().getNumBlueberries() >= amount) {
            Ant.getInstance().getEq().addMoney(amount * 15);
            Ant.getInstance().getEq().removeBlueberry(amount);
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

    public String upgradePick() {
        if (Ant.getInstance().getEq().getPickTier() == Constants.TIER_MARBLE)
            return "I already have the best pickaxe";
        if (Ant.getInstance().getEq().getPickTier() == Constants.TIER_SAND) {
            if (Ant.getInstance().getEq().getMoney() > Constants.TIER_STONE_PRICE) {
                Ant.getInstance().getEq().removeMoney(Constants.TIER_STONE_PRICE);
                Ant.getInstance().getEq().upgradePickTier();
                return "I have upgraded my pickaxe";
            }
            return "I don't have enough money";
        } else {
            if (Ant.getInstance().getEq().getMoney() > Constants.TIER_MARBLE_PRICE) {
                Ant.getInstance().getEq().removeMoney(Constants.TIER_MARBLE_PRICE);
                Ant.getInstance().getEq().upgradePickTier();
                return "I have upgraded my pickaxe";
            }
            return "I don't have enough money";
        }
    }
}
