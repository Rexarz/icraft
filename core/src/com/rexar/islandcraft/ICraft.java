package com.rexar.islandcraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.noise.NoiseGenerator;
import com.github.czyzby.noise4j.map.generator.util.Generators;
import com.badlogic.gdx.graphics.*;
import com.rexar.islandcraft.screens.GameScreen;
import com.rexar.islandcraft.utils.AssetsManager;

public class ICraft extends Game {


    private SpriteBatch batch;
    private Texture texture;

//    @Override
//    public void create() {
//        final Pixmap grid = new Pixmap(768, 768, Pixmap.Format.RGBA8888);
//        final Grid grid = new Grid(768);
//
//        final NoiseGenerator noiseGenerator = new NoiseGenerator();
//        noiseStage(grid, noiseGenerator, 32, 0.6f);
//        noiseStage(grid, noiseGenerator, 16, 0.2f);
//        noiseStage(grid, noiseGenerator, 8, 0.1f);
//        noiseStage(grid, noiseGenerator, 4, 0.1f);
//        noiseStage(grid, noiseGenerator, 1, 0.05f);
//        noiseStage(grid, noiseGenerator, 1, 0.01f);
//
//        for (int x = 0; x < grid.getWidth(); x++) {
//            for (int y = 0; y < grid.getHeight(); y++) {
//                final float cell = grid.get(x, y);
//                if (cell > 0.3f + (0.4f * distanceSquared(x, y, grid))) {
//                    if (cell > 0.5) {
//                        if (cell > 0.61f) {
//                            grid.drawPixel(x, y, Color.rgba8888(0, cell - 0.2f, 0, 1));
//                        } else
//                            grid.drawPixel(x, y, Color.rgba8888(0, cell, 0, 1));
//                    } else {
//                        grid.drawPixel(x, y, Color.rgba8888(cell, cell, 0, 1));
//                    }
//                } else {
//                    grid.set(x, y, 0f);
//                    grid.drawPixel(x, y, Color.rgba8888(0, 0, cell, 1));
//                }
//            }
//        }
//
//        texture = new Texture(grid);
//        batch = new SpriteBatch();
//
//
//        Pixmap print = texture.getTextureData().consumePixmap();
//        PixmapIO.writePNG(FileHandle.tempFile("mapNew.png"), print);
//
//
//        grid.dispose();
//    }

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

    @Override
    public void create() {
        new AssetsManager();
		setScreen(new GameScreen(this));
    }


//    @Override
//    public void render() {
//        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
//        batch.draw(texture, 0f, 0f);
//        batch.end();
//    }
//
//    @Override
//    public void dispose() {
//        texture.dispose();
//        batch.dispose();
//    }
}
