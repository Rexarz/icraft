package com.rexar.islandcraft;

import com.badlogic.gdx.Game;
import com.rexar.islandcraft.screens.GameScreen;
import com.rexar.islandcraft.utils.AssetsManager;

public class ICraft extends Game {

    @Override
    public void create() {
        new AssetsManager();
		setScreen(new GameScreen(this));
    }


}
