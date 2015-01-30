package com.ant.very.objects.map;

import com.ant.very.objects.MapEntity;
import com.ant.very.utils.Constants;

/**
 * Created by divoolej on 18.12.14.
 */
public class Bedrock extends MapEntity {
    public String onLook() {
        return "I see bedrock, impassable";
    }
    public String onDig() {
        return "I will never be able to dig through this";
    }
    public String onWalk() {
        return "There is a bedrock in the way";
    }
    public Bedrock() {
        spriteId = Constants.Sprites.BEDROCK.toInt();
    }
}
