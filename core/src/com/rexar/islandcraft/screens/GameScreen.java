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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexar.islandcraft.ICraft;
import com.rexar.islandcraft.gameobjects.Player;
import com.rexar.islandcraft.utils.AssetsManager;
import com.rexar.islandcraft.utils.Constants;
import com.rexar.islandcraft.utils.MapGenerator;
import com.rexar.islandcraft.utils.ScreenRenderer;

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


    private MapGenerator mapGenerator;
    private ScreenRenderer renderer;


    private Texture texture;

    public GameScreen(ICraft game) {
        this.game = game;

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(Constants.V_WIDTH / Constants.PPM, Constants.V_HEIGHT / Constants.PPM, gameCam);


        player = new Player(100, 100);

        mapGenerator = new MapGenerator();


        mapGenerator.mapGenerate();

        renderer = new ScreenRenderer(player, mapGenerator, viewport, gameCam);

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
        Gdx.gl.glClearColor(0, 0, 0.3f, 1);
        gameCam.position.set(player.position.x, player.position.y, 0);
        update(delta);
        gameCam.update();

//        shapeRenderer.setAutoShapeType(true);
//        shapeRenderer.setProjectionMatrix(gameCam.combined);
//        shapeRenderer.begin();
//        shapeRenderer.rect(player.playerBounds.x, player.playerBounds.y, player.playerBounds.width, player.playerBounds.height);
//        shapeRenderer.rect(wallRect.x, wallRect.y, wallRect.width, wallRect.height);
//        shapeRenderer.end();


        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();

//        batch.draw(new TextureRegion(AssetsManager.sprites, 0, 0, 17, 17), 0, 0);
//        new Sprite(new TextureRegion(AssetsManager.sprites, 0, 0, 17, 17).getTexture(),0,0).draw(batch);

        renderer.draw(batch);

        player.draw(batch);
//        wall.draw(batch);
        batch.end();

//        System.out.println(viewport.getWorldWidth());
//        System.out.println(viewport.getWorldHeight());
//        System.out.println(gameCam.position);
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
