package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

public class Cherry extends MapEntity {
    private int x, y;

    public String onLook() {
        return "a cherry";
    }

    public String onWalk() {
        return "I walked on a cherry";
    }

    public String onDig() {
        if (Ant.getInstance().getEq().getCurrentFuel() > 0) {
            if (Ant.getInstance().getEq().getFreeSpace() > 0) {
                WorldMap.getInstance().setEmpty(x, y);
                Ant.getInstance().moveTo(x, y);
                Ant.getInstance().getEq().addCherry();
                Ant.getInstance().getEq().burnFuel(1);
                return "I picked up a cherry";
            } else
                return "I don't have enough space in my inventory";
        } else
            return "I don't have enough fuel";
    }
    public Cherry(int x, int y) {
        spriteId = Constants.Sprites.CHERRY.toInt();
        this.x = x;
        this.y = y;
    }
}
