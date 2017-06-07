package com.rexar.islandcraft.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.rexar.islandcraft.utils.AssetsManager;

/**
 * Created by sergei.ivanishin on 6/2/2017.
 */
public class Tree extends NatureObjects {


    public Tree(Texture sprites, int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
        super(sprites, srcX, srcY, srcWidth, srcHeight, x, y);
        setPosition(x, y);
        setBounds(0, 0, 4, 4);


        health = 100f;
        currentState = NatureObjectStates.LIVE;
    }


    @Override
    public void getDamage(float damage) throws InterruptedException {
        super.getDamage(damage);
        if (currentState == NatureObjectStates.DEAD) {
            setRegion(103, 69, 33, 33);
//            setBounds(0, 0, 2, 2);
        }
    }
}
