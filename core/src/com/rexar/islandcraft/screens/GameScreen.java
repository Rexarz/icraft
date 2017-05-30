package com.rexar.islandcraft.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexar.islandcraft.ICraft;
import com.rexar.islandcraft.gameobjects.Player;
import com.rexar.islandcraft.utils.AssetsManager;
import com.rexar.islandcraft.utils.Constants;
import com.rexar.islandcraft.utils.MapGenerator;

/**
 * Created by rexar on 26.05.17.
 */
public class GameScreen implements Screen {


    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera gameCam;
    private Viewport viewport;
    private ICraft game;

    private AssetsManager assetsManager;
    private Player player;


    private Sprite wall;


    private Texture texture;

    public GameScreen(ICraft game) {
        this.game = game;

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, gameCam);


        texture = new Texture("badlogic.jpg");


        player = new Player(0, 0);

        wall = new Sprite();
        wall.setRegion(new TextureRegion(AssetsManager.sprites, 69, 0, 17, 17));
        wall.setBounds(0, 0, 20, 20);
        wall.setPosition(30, 30);


        MapGenerator.mapGenerate();


    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        player.update(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0, 1);

        update(delta);
        gameCam.update();

        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.setProjectionMatrix(gameCam.combined);
        shapeRenderer.begin();
        shapeRenderer.rect(player.playerBounds.x, player.playerBounds.y, player.playerBounds.width, player.playerBounds.height);
        shapeRenderer.end();


        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();


//        batch.draw(new TextureRegion(AssetsManager.sprites, 0, 0, 17, 17), 0, 0);
//        new Sprite(new TextureRegion(AssetsManager.sprites, 0, 0, 17, 17).getTexture(),0,0).draw(batch);

        player.draw(batch);
        wall.draw(batch);
        batch.end();


    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
