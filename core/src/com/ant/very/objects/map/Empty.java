package com.ant.very.objects.map;

import com.ant.very.WorldMap;
import com.ant.very.objects.Ant;
import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

/**
 * Created by divoolej on 18.12.14.
 */
public class Empty extends MapEntity {
    private int x, y;
    public void onLook() {}
    public void onInteract() {
        MapEntity m = WorldMap.getInstance().at(x, y);
        m = new Empty(x, y);
        Ant.getInstance().move(x, y);
    }
    public Empty(int x, int y) {
        spriteId = Constants.Sprites.EMPTY.toInt();
        this.x = x;
        this.y = y;
    }
}
