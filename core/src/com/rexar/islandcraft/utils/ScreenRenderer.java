package com.rexar.islandcraft.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexar.islandcraft.gameobjects.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei.ivanishin on 5/30/2017.
 */
public class ScreenRenderer {
    private Player player;
    private MapGenerator mapGenerator;
    private Viewport viewport;
    private OrthographicCamera camera;

    private List<Sprite> sprites;


    public ScreenRenderer(Player player, MapGenerator mapGenerator, Viewport viewport, OrthographicCamera camera) {
        this.player = player;
        this.mapGenerator = mapGenerator;
        this.viewport = viewport;
        this.camera = camera;

        sprites = new ArrayList<Sprite>();
        generateSprites();


    }

    public void draw(SpriteBatch batch) {

        float leftBorderX = camera.position.x - (camera.viewportWidth / 2f) - 2f;
        float rightBorderX = camera.position.x + (camera.viewportWidth / 2f) + 2f;
        float upBorderY = camera.position.y + (camera.viewportHeight / 2f) + 2f;
        float downBorderY = camera.position.y - (camera.viewportHeight / 2f) - 2f;


        int objectCounter = 0;


        for (int i = (int) leftBorderX; i < rightBorderX; i++) {
            for (int j = (int) downBorderY; j < upBorderY; j++) {
                if (i >= 0 && i <= mapGenerator.grid.getWidth()) {
                    if (j >= 0 && j <= mapGenerator.grid.getHeight()) {


                        if (mapGenerator.grid.get(i, j) > 0f) {
                            sprites.get(objectCounter).setPosition((float) i * 2, (float) j * 2);
                            sprites.get(objectCounter).draw(batch);
                            objectCounter++;
                        }

                    }

                }
            }
        }
        System.out.println(objectCounter);
    }

    public void generateSprites() {
        for (int i = 0; i < 300000; i++) {
            Sprite sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 69, 0, 17, 17));
            sprite.setBounds(0, 0, 2f, 2f);
            sprites.add(sprite);
        }
    }


    public boolean inCamBorders(int x, int y) {
        boolean result = false;
        if ((x < player.position.x - (viewport.getWorldWidth() / 2)) && (x > player.position.x + (viewport.getWorldWidth() / 2))) {
            if ((y < player.position.y - (viewport.getWorldHeight() / 2)) && (x > player.position.y + (viewport.getWorldHeight() / 2))) {
                return true;
            }
        }

        return result;
    }

}
