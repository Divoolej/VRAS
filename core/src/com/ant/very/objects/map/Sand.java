package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

public class Sand extends MapEntity {
    private int x, y;
    public void onLook() {}
    public void onInteract() {
        if (Ant.getInstance().getEq().getCurrentFuel() > 1) {
            WorldMap.getInstance().setEmpty(x, y);
            Ant.getInstance().moveTo(x, y);
            Ant.getInstance().getEq().burnFuel(2);
        }
    }
    public Sand(int x, int y) {
        spriteId = Constants.Sprites.SAND.toInt();
        this.x = x;
        this.y = y;
    }
}
