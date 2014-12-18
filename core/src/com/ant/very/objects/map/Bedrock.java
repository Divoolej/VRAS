package com.ant.very.objects.map;

import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

/**
 * Created by divoolej on 18.12.14.
 */
public class Bedrock extends MapEntity {
    public void onLook() {}
    public void onInteract() {}
    public Bedrock() {
        spriteId = Constants.Sprites.BEDROCK.toInt();
    }
}
