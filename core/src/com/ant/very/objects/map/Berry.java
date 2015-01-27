package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

public class Berry extends MapEntity {
    private int x, y;

    public String onLook() {
        return "a berry";
    }

    public String onWalk() {
        return "I walked on a berry";
    }

    public String onDig() {
        if (Ant.getInstance().getEq().getCurrentFuel() > 0) {
            if (Ant.getInstance().getEq().getFreeSpace() > 0) {
                WorldMap.getInstance().setEmpty(x, y);
                Ant.getInstance().moveTo(x, y);
                Ant.getInstance().getEq().addBerry();
                Ant.getInstance().getEq().burnFuel(1);
                return "I picked up a berry";
            } else
                return "I don't have enough space in my inventory";
        } else
            return "I don't have enough fuel";
    }
    public Berry(int x, int y) {
        spriteId = Constants.Sprites.BERRY.toInt();
        this.x = x;
        this.y = y;
    }
}
