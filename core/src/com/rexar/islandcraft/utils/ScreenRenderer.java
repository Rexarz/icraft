package com.rexar.islandcraft.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.czyzby.noise4j.map.Grid;
import com.rexar.islandcraft.gameobjects.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sergei.ivanishin on 5/30/2017.
 */
public class ScreenRenderer {
    private Player player;
    private MapGenerator mapGenerator;
    private Viewport viewport;
    private OrthographicCamera camera;

    private List<Sprite> sprites;
    private Random random = new Random();


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
        int treesCounter = 0;

        float offsetX = 0;
        float offsetY = 0;

//        for (int i = 100; i < 500; i++) {
//            for (int j = 100; j < 500; j++) {
//                if (mapGenerator.grid.get(i, j) > 0) {
//                    sprites.get(objectCounter).setPosition(i, j);
//                    sprites.get(objectCounter).setColor(0, 1, 0, 1);
//                    sprites.get(objectCounter).draw(batch);
//                    objectCounter++;
//                }
//                if (i > (camera.position.x + (camera.viewportWidth/2))){
//                    break;
//                }
//            }
//        }

        for (int i = (int) rightBorderX; i > leftBorderX; i--) {
            for (int j = (int) upBorderY; j > downBorderY; j--) {
                offsetX = i * 8f;
                offsetY = j * 8f;
                if (i >= 0 && i <= mapGenerator.grid.getWidth() && j >= 0 && j <= mapGenerator.grid.getHeight() && mapGenerator.grid.get(i, j) > 0f) {





                    sprites.get(objectCounter).setPosition((float) i, (float) j);
                    if (mapGenerator.grid.get(i, j) > 0.65f){
                        sprites.get(objectCounter).setColor(mapGenerator.grid.get(i, j),mapGenerator.grid.get(i, j),  mapGenerator.grid.get(i, j), 1);
                    }else if (mapGenerator.grid.get(i, j) > 0.48f) {
                        sprites.get(objectCounter).setColor(0, mapGenerator.grid.get(i, j), 0, 1);
                    } else if (mapGenerator.grid.get(i, j) > 0.1f) {
                        sprites.get(objectCounter).setColor(mapGenerator.grid.get(i, j), mapGenerator.grid.get(i, j), 0, 1);
                    }

                    sprites.get(objectCounter).draw(batch);
                    objectCounter++;


                }

                if (mapGenerator.grid.get(i,j) > 0.48f && mapGenerator.grid.get(i,j) < 0.55f){
                    mapGenerator.trees.get(treesCounter).setPosition(i,j);
                    mapGenerator.trees.get(treesCounter).draw(batch);
                    treesCounter++;
                }

//                draw(sprites.get(0).setPosition(i,j));

            }
        }

//        System.out.println(objectCounter);

        if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            mapGenerator.grid = new Grid(512);
            mapGenerator.mapGenerate();
        }
    }

    public void generateSprites() {
        for (int i = 0; i < 800000; i++) {
            float seed = random.nextFloat();
            Sprite sprite = null;
            if (seed > 0.9) {
                sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 69, 1, 8, 8));
            } else if (seed > 0.6) {
                sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 69 + 8, 1, 8, 8));
            } else {
                sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 69, 9, 8, 8));
            }
            sprite.setBounds(0, 0, 1, 1);
            sprites.add(sprite);
        }
    }


    public boolean inCamBorders(int x, int y) {
        boolean result = false;
        float leftBorderX = camera.position.x - (camera.viewportWidth / 2f);
        float rightBorderX = camera.position.x + (camera.viewportWidth / 2f);
        float upBorderY = camera.position.y + (camera.viewportHeight / 2f);
        float downBorderY = camera.position.y - (camera.viewportHeight / 2f);

        if (x > leftBorderX && x < rightBorderX && y > downBorderY && y < upBorderY) {
            return true;
        }

        return result;
    }

}
