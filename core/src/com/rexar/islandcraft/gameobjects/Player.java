package com.rexar.islandcraft.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rexar.islandcraft.objects.Tree;
import com.rexar.islandcraft.utils.AssetsManager;
import com.rexar.islandcraft.utils.MapGenerator;

/**
 * Created by sergei.ivanishin on 5/29/2017.
 */
public class Player extends Sprite {

    public Rectangle playerBounds;


    private Animation<TextureRegion> playerRun;
    private Animation<TextureRegion> playerIdleUp;
    private Animation<TextureRegion> playerIdleDown;
    private Animation<TextureRegion> playerIdleRight;

    private Animation<TextureRegion> playerRunRight;
    private Animation<TextureRegion> playerRunDown;
    private Animation<TextureRegion> playerRunUp;
    private float stateTime;


    public Vector2 position;
    private PlayerStates currentState;
    private PlayerStates previousState;

    private TextureRegion playerStay;

    private MapGenerator mapGenerator;


    private final float VELOCITY = 0.2f;


    private boolean canWalkUp = true;
    private boolean canWalkDown = true;
    private boolean canWalkLeft = true;
    private boolean canWalkRight = true;


    private enum PlayerStates {
        IDLE,
        RUNNING_R,
        RUNNING_L,
        RUNNING_U,
        RUNNING_D
    }


    public Player(float x, float y, MapGenerator mapGenerator) {
        stateTime = 0;

        this.mapGenerator = mapGenerator;


        this.position = new Vector2(x, y);


        setRegion(new TextureRegion(AssetsManager.sprites, 17, 17));
        setBounds(0f, 0f, 2f, 2f);
        setPosition(x, y);
        currentState = PlayerStates.IDLE;
        previousState = PlayerStates.IDLE;

        playerBounds = new Rectangle(0, 0, 17f / 100f, 17f / 100f);

        defineAnimations();


        Gdx.app.log("Player", "Player created.");
    }

    private void defineAnimations() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(AssetsManager.sprites, 0, 0, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 0, 17, 17, 17));
        playerIdleDown = new Animation<TextureRegion>(1 / 4f, frames);

        frames.clear();
        frames.add(new TextureRegion(AssetsManager.sprites, 17, 0, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 17, 17, 17, 17));
        playerIdleUp = new Animation<TextureRegion>(1 / 4f, frames);

        frames.clear();
        frames.add(new TextureRegion(AssetsManager.sprites, 34, 0, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 34, 17, 17, 17));
        playerIdleRight = new Animation<TextureRegion>(1 / 4f, frames);


        frames.clear();
        frames.add(new TextureRegion(AssetsManager.sprites, 34, 34, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 34, 51, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 34, 68, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 34, 85, 17, 17));
        playerRunRight = new Animation<TextureRegion>(1 / 8f, frames);


        frames.clear();
        frames.add(new TextureRegion(AssetsManager.sprites, 0, 34, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 0, 51, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 0, 68, 17, 17));
        playerRunDown = new Animation<TextureRegion>(1 / 8f, frames);

        frames.clear();
        frames.add(new TextureRegion(AssetsManager.sprites, 17, 34, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 17, 51, 17, 17));
        frames.add(new TextureRegion(AssetsManager.sprites, 17, 68, 17, 17));
        playerRunUp = new Animation<TextureRegion>(1 / 8f, frames);

    }

    public void playerMovements() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && canWalkRight) {
            position.x += VELOCITY;
            setRegion(playerRunRight.getKeyFrame(stateTime, true));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && canWalkLeft) {
            position.x -= VELOCITY;
            setRegion(playerRunRight.getKeyFrame(stateTime, true));
            if (isFlipX()) {

            } else {
                flip(true, false);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && canWalkUp) {
            position.y += VELOCITY;
            setRegion(playerRunUp.getKeyFrame(stateTime, true));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && canWalkDown) {
            position.y -= VELOCITY;
            setRegion(playerRunDown.getKeyFrame(stateTime, true));
        }
    }

    public void update(float delta) {

        playerBounds.setPosition(position);

        stateTime += Gdx.graphics.getDeltaTime();
        setPosition(position.x, position.y);


        if (mapGenerator.objects[(int) position.x][(int) (position.y + 1)] != 0) {
            canWalkUp = false;
        } else if (mapGenerator.objects[(int) position.x][(int) (position.y - 1)] != 0) {
            canWalkDown = false;
        } else if (mapGenerator.objects[(int) position.x + 1][(int) (position.y)] != 0) {
            canWalkRight = false;
        } else if (mapGenerator.objects[(int) position.x - 1][(int) (position.y)] != 0) {
            canWalkLeft = false;
        }else if (mapGenerator.objects[(int) position.x][(int) (position.y - 1)] == 0) {
            canWalkDown = true;
        } else if (mapGenerator.objects[(int) position.x + 1][(int) (position.y)] == 0) {
            canWalkRight = true;
        } else if (mapGenerator.objects[(int) position.x - 1][(int) (position.y)] == 0) {
            canWalkLeft = true;
        } else if (mapGenerator.objects[(int) position.x][(int) (position.y + 1)] == 0) {
            canWalkUp = true;
        }

        playerMovements();
    }


}
