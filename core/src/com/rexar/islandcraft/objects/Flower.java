package com.rexar.islandcraft.objects;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by sergei.ivanishin on 6/5/2017.
 */
public class Flower extends NatureObjects {
    public Flower(Texture sprites, int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
        super(sprites, srcX, srcY, srcWidth, srcHeight, x, y);
        setPosition(x, y);
        setBounds(0,0,0.7f,0.5f);
    }
}
