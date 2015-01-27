package com.ant.very.objects;

/**
 * Created by divoolej on 17.12.14.
 */

public abstract class MapEntity {
    protected int spriteId;

    public abstract String onWalk();
    public abstract String onDig();
    public abstract String onLook();

    public int getSpriteId() {
        return spriteId;
    }
}
