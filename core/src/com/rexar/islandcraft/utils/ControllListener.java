package com.rexar.islandcraft.utils;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.rexar.islandcraft.gameobjects.Player;

/**
 * Created by Rexar on 05.06.2017.
 */
public class ControllListener extends InputAdapter {

    private Player player;
    private OrthographicCamera camera;

    public ControllListener(Player player, OrthographicCamera camera) {
        this.player = player;
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean scrolled(int amount) {
        float MAX_ZOOM_IN = 2f;
        float MAX_ZOOM_OUT = 2f;

        camera.zoom += amount;
        camera.update();
        return super.scrolled(amount);

    }


}
