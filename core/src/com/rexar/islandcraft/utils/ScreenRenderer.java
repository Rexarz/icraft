package com.rexar.islandcraft.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.czyzby.noise4j.map.Grid;
import com.rexar.islandcraft.gameobjects.Player;

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

//        sprites = new ArrayList<Sprite>();
//        generateSprites();


    }

    public void draw(SpriteBatch batch) {

        float leftBorderX = camera.position.x - (camera.viewportWidth / 2f) - 5f;
        float rightBorderX = camera.position.x + (camera.viewportWidth / 2f) + 5f;
        float upBorderY = camera.position.y + (camera.viewportHeight / 2f) + 5f;
        float downBorderY = camera.position.y - (camera.viewportHeight / 2f) - 5f;

        int objectCounter = 0;
        int groundType_0 = 0;
        int groundType_1 = 0;
        int groundType_2 = 0;
        int groundType_3 = 0;
        int treeType_0 = 0;
        int treeType_1 = 0;
        int stoneType_0 = 0;
        int stoneType_1 = 0;
        int flowerType_0 = 0;

        float offsetX = 0;
        float offsetY = 0;


        for (int i = (int) upBorderY; i > downBorderY; i--) {
            for (int j = (int) leftBorderX; j < rightBorderX; j++) {

                if (j >= 0 && j <= mapGenerator.grid.getWidth() && i >= 0 && i <= mapGenerator.grid.getHeight() && mapGenerator.grid.get(j, i) > 0f) {

                    if (mapGenerator.map[j][i] > 0.65f) {
                        mapGenerator.grounds.get(groundType_0).setPosition(j, i);
                        mapGenerator.grounds.get(groundType_0).setColor(mapGenerator.grid.get(j, i), mapGenerator.grid.get(j, i), mapGenerator.grid.get(j, i), 1);
                        mapGenerator.grounds.get(groundType_0).draw(batch);
                        groundType_0++;
                    } else if (mapGenerator.map[j][i] > 0.48f) {
                        mapGenerator.grounds.get(groundType_0).setPosition(j, i);
                        mapGenerator.grounds.get(groundType_0).setColor(0, mapGenerator.grid.get(j, i), 0, 1);
                        mapGenerator.grounds.get(groundType_0).draw(batch);
                        groundType_0++;
                    } else if (mapGenerator.map[j][i] > 0.1f) {
                        mapGenerator.grounds.get(groundType_0).setPosition(j, i);
                        mapGenerator.grounds.get(groundType_0).setColor(mapGenerator.grid.get(j, i), mapGenerator.grid.get(j, i), 0, 1);
                        mapGenerator.grounds.get(groundType_0).draw(batch);
                        groundType_0++;
                    }
                }
            }
        }

//        __________________________


        for (int i = (int) upBorderY; i > player.position.y; i--) {
            for (int j = (int) leftBorderX; j < rightBorderX; j++) {
                if (j >= 0 && j <= mapGenerator.grid.getWidth() && i >= 0 && i <= mapGenerator.grid.getHeight() && mapGenerator.grid.get(j, i) > 0f) {
                    try {


                        if (mapGenerator.objects[j][i] == 1) {
                            mapGenerator.natureObjects[j][i].setPosition(j - 0.5f, i + 0.5f);
                            mapGenerator.natureObjects[j][i].update();
                            mapGenerator.natureObjects[j][i].draw(batch);
                            treeType_0++;
                        } else if (mapGenerator.objects[j][i] == 2) {
                            mapGenerator.natureObjects[j][i].setPosition(j - 0.5f, i);
                            mapGenerator.natureObjects[j][i].update();
                            mapGenerator.natureObjects[j][i].draw(batch);
                            treeType_1++;
                        } else if (mapGenerator.objects[j][i] == 3) {
                            mapGenerator.stoneType_0.get(stoneType_0).setPosition(j, i);
                            mapGenerator.natureObjects[j][i].update();
                            mapGenerator.stoneType_0.get(stoneType_0).draw(batch);
                            stoneType_0++;
                        } else if (mapGenerator.objects[j][i] == 4) {
                            mapGenerator.stoneType_1.get(stoneType_1).setPosition(j, i);
                            mapGenerator.natureObjects[j][i].update();
                            mapGenerator.stoneType_1.get(stoneType_1).draw(batch);
                            stoneType_1++;
                        } else if (mapGenerator.objects[j][i] == 5) {
                            mapGenerator.flowerType_0.get(flowerType_0).setPosition(j, i);
                            mapGenerator.flowerType_0.get(flowerType_0).draw(batch);
                            flowerType_0++;
                        }

                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }

        player.draw(batch);

        for (int i = (int) (player.position.y); i > downBorderY; i--) {
            for (int j = (int) leftBorderX; j < rightBorderX; j++) {
                if (j >= 0 && j <= mapGenerator.grid.getWidth() && i >= 0 && i <= mapGenerator.grid.getHeight() && mapGenerator.grid.get(j, i) > 0f) {
                    try {


                        if (mapGenerator.objects[j][i] == 1) {
//                            mapGenerator.treesType_0.get(treeType_0).setPosition(j, i);
//                            mapGenerator.treesType_0.get(treeType_0).draw(batch);
                            mapGenerator.natureObjects[j][i].setPosition(j - 0.5f, i + 0.5f);
                            mapGenerator.natureObjects[j][i].update();
                            mapGenerator.natureObjects[j][i].draw(batch);
                            treeType_0++;
                        } else if (mapGenerator.objects[j][i] == 2) {
                            mapGenerator.natureObjects[j][i].setPosition(j - 0.5f, i);
                            mapGenerator.natureObjects[j][i].update();
                            mapGenerator.natureObjects[j][i].draw(batch);
                            treeType_1++;
                        } else if (mapGenerator.objects[j][i] == 3) {
                            mapGenerator.stoneType_0.get(stoneType_0).setPosition(j, i);
                            mapGenerator.stoneType_0.get(stoneType_0).draw(batch);
                            stoneType_0++;
                        } else if (mapGenerator.objects[j][i] == 4) {
                            mapGenerator.stoneType_1.get(stoneType_1).setPosition(j, i);
                            mapGenerator.stoneType_1.get(stoneType_1).draw(batch);
                            stoneType_1++;
                        } else if (mapGenerator.objects[j][i] == 5) {
                            mapGenerator.flowerType_0.get(flowerType_0).setPosition(j, i);
                            mapGenerator.flowerType_0.get(flowerType_0).draw(batch);
                            flowerType_0++;
                        }

                    } catch (IndexOutOfBoundsException e) {
                    }
                }
            }
        }


//        __________________________


//        for (int i = (int) upBorderY; i > downBorderY; i--) {
//            for (int j = (int) leftBorderX; j < rightBorderX; j++) {
//                if (j >= 0 && j <= mapGenerator.grid.getWidth() && i >= 0 && i <= mapGenerator.grid.getHeight() && mapGenerator.grid.get(j, i) > 0f) {
//                    try {
//
//
//                        if (mapGenerator.objects[j][i] == 1) {
//                            mapGenerator.treesType_0.get(treeType_0).setPosition(j, i);
//                            mapGenerator.treesType_0.get(treeType_0).draw(batch);
//                            treeType_0++;
//                        } else if (mapGenerator.objects[j][i] == 2) {
//                            mapGenerator.treesType_1.get(treeType_1).setPosition(j, i);
//                            mapGenerator.treesType_1.get(treeType_1).draw(batch);
//                            treeType_1++;
//                        } else if (mapGenerator.objects[j][i] == 3) {
//                            mapGenerator.stoneType_0.get(stoneType_0).setPosition(j, i);
//                            mapGenerator.stoneType_0.get(stoneType_0).draw(batch);
//                            stoneType_0++;
//                        } else if (mapGenerator.objects[j][i] == 4) {
//                            mapGenerator.stoneType_1.get(stoneType_1).setPosition(j, i);
//                            mapGenerator.stoneType_1.get(stoneType_1).draw(batch);
//                            stoneType_1++;
//                        } else if (mapGenerator.objects[j][i] == 5) {
//                            mapGenerator.flowerType_0.get(flowerType_0).setPosition(j, i);
//                            mapGenerator.flowerType_0.get(flowerType_0).draw(batch);
//                            flowerType_0++;
//                        }
//
//                    } catch (IndexOutOfBoundsException e) {
//                    }
//                }
//            }
//        }


        if (Gdx.input.isKeyPressed(Input.Keys.G)) {
            mapGenerator.grid = new Grid(512);
            mapGenerator.mapGenerate();
        }
    }

//    public void generateSprites() {
//        for (int i = 0; i < 800000; i++) {
//            float seed = random.nextFloat();
//            Sprite sprite = null;
//            if (seed > 0.9) {
//                sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 69, 1, 8, 8));
//            } else if (seed > 0.6) {
//                sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 69 + 8, 1, 8, 8));
//            } else {
//                seed = random.nextFloat();
//                if (seed > 0.5f) {
//                    sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 77, 9, 8, 8));
//                } else {
//                    sprite = new Sprite(new TextureRegion(AssetsManager.sprites, 69, 9, 8, 8));
//                }
//            }
//            sprite.setBounds(0, 0, 1, 1);
//            sprites.add(sprite);
//        }
//    }


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
