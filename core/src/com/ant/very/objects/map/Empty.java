package com.ant.very.objects.map;

import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

public class Empty extends MapEntity {
    private int x, y;
    public void onLook() {}
    public void onInteract() {
        if (Ant.getInstance().getEq().getCurrentFuel() > 0) {
            Ant.getInstance().moveTo(x, y);
            Ant.getInstance().getEq().burnFuel(1);
        }
    }
    public Empty(int x, int y) {
        spriteId = Constants.Sprites.EMPTY.toInt();
        this.x = x;
        this.y = y;
    }
}
