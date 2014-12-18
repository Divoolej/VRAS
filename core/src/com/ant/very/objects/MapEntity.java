package com.ant.very.objects;

/**
 * Created by divoolej on 17.12.14.
 */

public abstract class MapEntity {
    protected int spriteId;

    public abstract void onInteract();
    public abstract void onLook();

    public int getSpriteId() {
        return spriteId;
    }
}
