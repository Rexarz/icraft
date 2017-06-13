package com.rexar.islandcraft.utils;

import com.badlogic.gdx.Gdx;
import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.noise.NoiseGenerator;
import com.github.czyzby.noise4j.map.generator.util.Generators;
import com.rexar.islandcraft.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sergei.ivanishin on 5/29/2017.
 */
public class MapGenerator {

    //    GROUND
    public List<Tile> grounds;


    //    TREES
    public NatureObjects[][] natureObjects;
    public List<Tree> treesType_0;
    public List<Tree> treesType_1;

    //    STONES
    public List<Stone> stoneType_0;
    public List<Stone> stoneType_1;

    //    FLOWERS
    public List<Flower> flowerType_0;

    //    GROUND
    public Ground[][] ground;


    public Grid grid;
    public float[][] map;
    public int[][] objects;

    private int objectsCounter;

    Random random = new Random();

    public MapGenerator() {
//        GROUND
        grounds = new ArrayList<Tile>();
        ground = new Ground[Constants.MAP_SIZE][Constants.MAP_SIZE];


//        TREES
        natureObjects = new NatureObjects[Constants.MAP_SIZE][Constants.MAP_SIZE];
        treesType_0 = new ArrayList<Tree>();
        treesType_1 = new ArrayList<Tree>();

//        STONES
        stoneType_0 = new ArrayList<Stone>();
        stoneType_1 = new ArrayList<Stone>();

//        FLOWERS
        flowerType_0 = new ArrayList<Flower>();

//        OTHER
        grid = new Grid(Constants.MAP_SIZE);
        map = new float[Constants.MAP_SIZE][Constants.MAP_SIZE];
        objects = new int[Constants.MAP_SIZE][Constants.MAP_SIZE];
        objectsCounter = 0;
    }

    public void mapGenerate() {

        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[i].length; j++) {
                objects[i][j] = 0;
                map[i][j] = 0;
                natureObjects[i][j] = null;
                ground[i][j] = null;
            }
        }


        final NoiseGenerator noiseGenerator = new NoiseGenerator();
        noiseStage(grid, noiseGenerator, 32, 0.6f);
        noiseStage(grid, noiseGenerator, 16, 0.2f);
        noiseStage(grid, noiseGenerator, 8, 0.1f);
        noiseStage(grid, noiseGenerator, 4, 0.1f);
        noiseStage(grid, noiseGenerator, 1, 0.05f);
        noiseStage(grid, noiseGenerator, 1, 0.01f);

        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                final float cell = grid.get(x, y);
                if (cell > 0.3f + (0.4f * distanceSquared(x, y, grid))) {
                    System.out.print(cell);
                    map[x][y] = cell;
                    generateGround(x, y);
                    generateForest(x, y);
                } else {
                    grid.set(x, y, 0f);
                    map[x][y] = 0f;
                }
            }
            System.out.println();
        }

        Gdx.app.log("Map", "Map generation done successfully");
    }

    private static float distanceSquared(int x, int y, Grid grid) {
        float dx = 2f * x / grid.getWidth() - 1f;
        float dy = 2f * y / grid.getHeight() - 1f;
        return dx * dx + dy * dy;
    }

    private void generateGround(int x, int y) {
        float rand = random.nextFloat();
        final float cell = grid.get(x, y);
        Ground newGround = null;

        newGround = checkBorders(x, y, rand);
        if (newGround == null) {
            newGround = generateCommonGround(x, y, rand, newGround);
        }
        ground[x][y] = newGround;
    }

    private Ground checkBorders(int x, int y, float rand) {
        Ground result = null;
        boolean upBorder = true;
        boolean downBorder = true;
        boolean leftBorder = true;
        boolean rightBorder = true;

        if (grid.get(x, y - 1) == 0) {
            downBorder = false;
        }
        if (grid.get(x, y + 1) == 0) {
            upBorder = false;
        }
//        if (grid.get(x + 1, y) == 0) {
//            rightBorder = false;
//            System.out.println("+");
//
//        }
        if (grid.get(x + 1, y) == 0f) {
            System.out.print("x - " + x + " ");
            System.out.print("y - " + y + " ");
            System.out.println();
        }

//        if (grid.get(x - 1, y) == 0) {
//            System.out.println(map[x][y] + "current x and y");
//            System.out.println(map[x - 1][y] + "next x");
//            rightBorder = false;
//        }
        if (grid.get(x - 1, y) == 0) {
            leftBorder = false;
        }
        if (grid.get(x, y) != 0) {
//            if (!leftBorder && !downBorder) {
//                result = new Ground(AssetsManager.sprites, 77, 18, 8, 8, x, y);
//                result.flip(false, true);
//            }
            if (!rightBorder) {
                result = new Ground(AssetsManager.sprites, 77, 18, 8, 8, x, y);
                result.flip(true, false);
                result.rotate90(true);
            }
        }

        return result;

    }

    private Ground generateCommonGround(int x, int y, float rand, Ground newGround) {
        if (rand > 0.6f) {
            newGround = new Ground(AssetsManager.sprites, 69, 1, 8, 8, x, y);
        } else if (rand > 0.3f) {
            newGround = new Ground(AssetsManager.sprites, 77, 1, 8, 8, x, y);
        } else if (rand > 0f) {
            newGround = new Ground(AssetsManager.sprites, 69, 9, 8, 8, x, y);
        }
        return newGround;
    }

    private void generateForest(int x, int y) {
        float rand = random.nextFloat();
        final float cell = grid.get(x, y);
        if (cell > 0.5f && cell < 0.55f) {
            if (rand < 0.2f) {
                Tree tree = new Tree(AssetsManager.sprites, 103, 1, 33, 33, x, y);
                treesType_0.add(tree);
                objects[x][y] = 1;
                natureObjects[x][y] = tree;
                objectsCounter++;
            }
        } else if (cell > 0.48f && cell < 0.50f) {
            if (rand < 0.1f) {
                Tree tree = new Tree(AssetsManager.sprites, 103, 35, 33, 33, x, y);
                treesType_1.add(tree);
                objects[x][y] = 2;
                natureObjects[x][y] = tree;
                objectsCounter++;
            }
        } else if (cell > 0.65f) {
            if (rand < 0.2f) {
                Stone stone = new Stone(AssetsManager.sprites, 137, 1, 10, 10, x, y);
                stoneType_0.add(stone);
                objects[x][y] = 3;
                natureObjects[x][y] = stone;
                objectsCounter++;
            } else if (rand < 0.3f) {
                Stone stone = new Stone(AssetsManager.sprites, 154, 1, 10, 10, x, y);
                stoneType_1.add(stone);
                objects[x][y] = 4;
                objectsCounter++;
            }
        } else if (cell > 0.55f && cell < 0.6f) {
            if (rand < 0.1f) {
                Flower flower = new Flower(AssetsManager.sprites, 137, 18, 7, 4, x, y);
                flowerType_0.add(flower);
                objects[x][y] = 5;
                objectsCounter++;
            }
        }
    }


    private static void noiseStage(final Grid grid, final NoiseGenerator noiseGenerator, final int radius,
                                   final float modifier) {
        noiseGenerator.setRadius(radius);
        noiseGenerator.setModifier(modifier);
        // Seed ensures randomness, can be saved if you feel the need to
        // generate the same grid in the future.
        noiseGenerator.setSeed(Generators.rollSeed());
        noiseGenerator.generate(grid);
    }

}



