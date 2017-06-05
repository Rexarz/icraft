package com.rexar.islandcraft.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by sergei.ivanishin on 6/5/2017.
 */
public class Tile extends Sprite {
    public Tile(Texture sprites, int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
        super(sprites, srcX, srcY, srcWidth, srcHeight);

        setPosition(x, y);
        setBounds(0,0,2,2);

    }
}
