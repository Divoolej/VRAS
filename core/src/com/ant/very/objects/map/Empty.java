package com.ant.very.objects.map;

import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

/**
 * Created by divoolej on 18.12.14.
 */
public class Empty extends MapEntity {
    public void onLook() {}
    public void onInteract() {}
    public Empty() {
        spriteId = Constants.Sprites.EMPTY.toInt();
    }
}
