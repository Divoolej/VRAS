package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

public class Raspberry extends MapEntity {
    private int x, y;
    public void onLook() {}
    public void onInteract() {
        if (Ant.getInstance().getEq().getCurrentFuel()  > 0) {
            WorldMap.getInstance().setEmpty(x, y);
            Ant.getInstance().moveTo(x, y);
            Ant.getInstance().getEq().addCherry();
            Ant.getInstance().getEq().burnFuel(1);
        }
    }
    public Raspberry(int x, int y) {
        spriteId = Constants.Sprites.CHERRY.toInt();
        this.x = x;
        this.y = y;
    }
}
