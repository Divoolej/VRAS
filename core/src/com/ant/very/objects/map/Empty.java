package com.ant.very.objects.map;

import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

public class Empty extends MapEntity {
    private int x, y;
    public void onLook() {}
    public void onInteract() {
        Ant.getInstance().moveTo(x, y);
    }
    public Empty(int x, int y) {
        spriteId = Constants.Sprites.EMPTY.toInt();
        this.x = x;
        this.y = y;
    }
}
