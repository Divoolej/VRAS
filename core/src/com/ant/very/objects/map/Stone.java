package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

/**
 * Created by divoolej on 18.12.14.
 */
public class Stone extends MapEntity {
    private int x, y;

    public String onLook() {
        return "a stone";
    }

    public String onWalk() {
        return "There is a stone in the way";
    }

    public String onDig() {
        if (Ant.getInstance().getEq().getPickTier() > 1) {
            if (Ant.getInstance().getEq().getCurrentFuel() > 2) {
                WorldMap.getInstance().setEmpty(x, y);
                Ant.getInstance().moveTo(x, y);
                Ant.getInstance().getEq().burnFuel(3);
                return "I successfully dug through the stone";
            } else
                return "I don't have that much fuel";
        } else {
            return "I am not able to dig through the stone yet";
        }
    }

    public Stone(int x, int y) {
        spriteId = Constants.Sprites.STONE.toInt();
        this.x = x;
        this.y = y;
    }
}