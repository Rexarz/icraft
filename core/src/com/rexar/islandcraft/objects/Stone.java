package com.rexar.islandcraft.objects;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by sergei.ivanishin on 6/5/2017.
 */
public class Stone extends NatureObjects {
    public Stone(Texture sprites, int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
        super(sprites, srcX, srcY, srcWidth, srcHeight, x, y);

        setPosition(x, y);
        setBounds(0, 0, 2, 2);

    }
}