package com.rexar.islandcraft.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.noise.NoiseGenerator;
import com.github.czyzby.noise4j.map.generator.util.Generators;

/**
 * Created by sergei.ivanishin on 5/29/2017.
 */
public class MapGenerator {

    public Grid grid;
    public Texture[][] tileMap;

    public MapGenerator() {
        grid = new Grid(512);
    }

    public void mapGenerate() {

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
                } else {
                    grid.set(x, y, 0f);
                }
            }
        }


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



