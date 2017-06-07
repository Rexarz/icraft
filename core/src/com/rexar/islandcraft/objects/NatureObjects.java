package com.rexar.islandcraft.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by sergei.ivanishin on 6/5/2017.
 */
public class NatureObjects extends Sprite {

    protected float health;

    public NatureObjectStates currentState;

    private float hitTime;
    private boolean getHited;


    public NatureObjects(Texture sprites, int srcX, int srcY, int srcWidth, int srcHeight, int x, int y) {
        super(sprites, srcX, srcY, srcWidth, srcHeight);
        hitTime = 0;
        getHited = false;
        setPosition(x, y);
        setBounds(0, 0, 4, 4);


    }

    public enum NatureObjectStates {
        LIVE,
        DEAD
    }

    public void getDamage(float damage) throws InterruptedException {
        hitTime = 0;
        health -= damage;
        getHited = true;
        setColor(1, 0, 0, 1);
        if (health <= 0) {
            currentState = NatureObjectStates.DEAD;
        }

    }

    public void update() {
        if (getHited) {
            hitTime += Gdx.graphics.getDeltaTime();
            if (hitTime > 0.07f) {
                setColor(1, 1, 1, 1);
            }
        }
    }
}
