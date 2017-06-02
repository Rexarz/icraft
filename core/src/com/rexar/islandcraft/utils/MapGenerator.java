package com.rexar.islandcraft.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.noise.NoiseGenerator;
import com.github.czyzby.noise4j.map.generator.util.Generators;
import com.rexar.islandcraft.objects.Tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sergei.ivanishin on 5/29/2017.
 */
public class MapGenerator {

    public Grid grid;
    public Sprite[][] map;
    public ArrayList<Tree> trees;

    Random random = new Random();

    public MapGenerator() {
        grid = new Grid(512);
        map = new Sprite[512][512];
        trees = new ArrayList<Tree>();
    }

    public void mapGenerate() {

        int treeCounter = 0;


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
                    if (cell > 0.48f && cell < 0.55f) {
                        if (random.nextFloat() > 0.8f) {
                            Tree tree = new Tree(AssetsManager.sprites, 103, 1, 33, 33, x, y);
                            trees.add(tree);
                            treeCounter++;
                        }
                    }
                } else {
                    grid.set(x, y, 0f);
                }
            }
        }

        System.out.println(treeCounter);
    }

    private static float distanceSquared(int x, int y, Grid grid) {
        float dx = 2f * x / grid.getWidth() - 1f;
        float dy = 2f * y / grid.getHeight() - 1f;
        return dx * dx + dy * dy;
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



