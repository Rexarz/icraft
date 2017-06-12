package com.rexar.islandcraft.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by sergei.ivanishin on 6/12/2017.
 */
public class Ground extends Sprite {
    public Ground(Texture sprites, int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
        super(sprites, srcX, srcY, srcWidth, srcHeight);

        setPosition(x, y);
        setBounds(0, 0, 1, 1);


    }
}
