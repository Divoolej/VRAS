package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

/**
 * Created by divoolej on 18.12.14.
 */
public class Marble extends MapEntity {
    private int x, y;

    public String onLook() {
        return "a block of marble";
    }

    public String onWalk() {
        return "There is a block of marble in the way";
    }

    public String onDig() {
        if (Ant.getInstance().getEq().getPickTier() > 2) {
            if (Ant.getInstance().getEq().getCurrentFuel() > 3) {
                WorldMap.getInstance().setEmpty(x, y);
                Ant.getInstance().moveTo(x, y);
                Ant.getInstance().getEq().burnFuel(4);
                return "I successfully dug through the marble";
            } else
                return "I don't have that much fuel";
        } else {
            return "I am not able to dig through the marble yet";
        }
    }

    public Marble(int x, int y) {
        int tmp_random = (int)(Math.random() * 2);
        spriteId = (tmp_random == 0 ?
                Constants.Sprites.MARBLE1.toInt() : Constants.Sprites.MARBLE2.toInt());
        this.x = x;
        this.y = y;
    }
}