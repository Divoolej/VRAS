package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

public class Sand extends MapEntity {
    private int x, y;
    public String onLook() {
        return "You see sand";
    }

    public String onWalk() {
        return "There is a block of sand in the way";
    }

    public String onDig() {
        if (Ant.getInstance().getEq().getCurrentFuel() > 1) {
            WorldMap.getInstance().setEmpty(x, y);
            Ant.getInstance().moveTo(x, y);
            Ant.getInstance().getEq().burnFuel(2);
            return "I successfully dug through the sand";
        }
        return "I don't have enough fuel";
    }
    public Sand(int x, int y) {
        spriteId = Constants.Sprites.SAND.toInt();
        this.x = x;
        this.y = y;
    }
}
