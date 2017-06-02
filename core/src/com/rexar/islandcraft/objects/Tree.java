package com.rexar.islandcraft.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by sergei.ivanishin on 6/2/2017.
 */
public class Tree extends Sprite {

    public Tree(Texture sprites, int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
        super(sprites, srcX, srcY, srcWidth, srcHeight);

        setPosition(x, y);
        setBounds(0,0,4,4);

    }
}
