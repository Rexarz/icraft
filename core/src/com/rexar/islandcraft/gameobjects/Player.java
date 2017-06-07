package com.rexar.islandcraft.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.rexar.islandcraft.objects.NatureObjects;
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
    private Animation<TextureRegion> playerIdleLeft;

    private Animation<TextureRegion> playerRunRight;
    private Animation<TextureRegion> playerRunDown;
    private Animation<TextureRegion> playerRunUp;
    private float stateTime;


    public Vector2 position;
    private PlayerStates currentState;
    private PlayerStates previousState;
    private Direction currentDirection;

    private TextureRegion playerStay;

    private MapGenerator mapGenerator;


    private final float VELOCITY = 0.2f;


    private boolean canWalkUp = true;
    private boolean canWalkDown = true;
    private boolean canWalkLeft = true;
    private boolean canWalkRight = true;
    private boolean running = false;

    private float damage = 50f;
    private float colorTime = 0;

    private enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    private enum PlayerStates {
        IDLE_D,
        IDLE_U,
        IDLE_R,
        IDLE_L,
        RUNNING_R,
        RUNNING_L,
        RUNNING_U,
        RUNNING_D,

    }


    public Player(float x, float y, MapGenerator mapGenerator) {
        stateTime = 0;

        this.mapGenerator = mapGenerator;


        this.position = new Vector2(x, y);


        setRegion(new TextureRegion(AssetsManager.sprites, 17, 17));
        setBounds(0f, 0f, 2f, 2f);
        setPosition(x, y);
        currentState = PlayerStates.IDLE_D;
        previousState = PlayerStates.IDLE_D;
        currentDirection = Direction.DOWN;

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
            currentState = PlayerStates.RUNNING_R;
            currentDirection = Direction.RIGHT;
            running = true;
            position.x += VELOCITY;
            setRegion(playerRunRight.getKeyFrame(stateTime, true));
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && canWalkLeft) {
            position.x -= VELOCITY;
            currentDirection = Direction.LEFT;
            running = true;
            setRegion(playerRunRight.getKeyFrame(stateTime, true));
            currentState = PlayerStates.RUNNING_L;
            if (isFlipX()) {

            } else {
                flip(true, false);
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && canWalkUp) {
            position.y += VELOCITY;
            currentDirection = Direction.UP;
            running = true;
            setRegion(playerRunUp.getKeyFrame(stateTime, true));
            currentState = PlayerStates.RUNNING_U;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && canWalkDown) {
            position.y -= VELOCITY;
            currentDirection = Direction.DOWN;
            running = true;
            setRegion(playerRunDown.getKeyFrame(stateTime, true));
            currentState = PlayerStates.RUNNING_D;
        } else {
            running = false;
            previousState = currentState;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            try {
                dealDamage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {

            this.setColor(1, 0, 0, 1);

        }
    }

    public void update(float delta) {

        if (colorTime > 0.5f) {
            this.setColor(1, 1, 1, 1);
        }

        playerBounds.setPosition(position);

        stateTime += Gdx.graphics.getDeltaTime();
        setPosition(position.x, position.y);


        checkCollision();
        playerMovements();
        getState();
    }

    private void getState() {

        if ((currentState == PlayerStates.IDLE_R || previousState == PlayerStates.RUNNING_R) && !running) {
            setRegion(playerIdleRight.getKeyFrame(stateTime, true));
        }
        if ((currentState == PlayerStates.IDLE_L || previousState == PlayerStates.RUNNING_L) && !running) {
            setRegion(playerIdleRight.getKeyFrame(stateTime, true));
            if (isFlipX()) {

            } else {
                flip(true, false);
            }
        }
        if ((currentState == PlayerStates.IDLE_D || previousState == PlayerStates.RUNNING_D) && !running) {
            setRegion(playerIdleDown.getKeyFrame(stateTime, true));
        }
        if ((currentState == PlayerStates.IDLE_U || previousState == PlayerStates.RUNNING_U) && !running) {
            setRegion(playerIdleUp.getKeyFrame(stateTime, true));
        }
    }

    private void checkCollision() {

        if (mapGenerator.natureObjects[(int) position.x][(int) (position.y + 1)] != null) {
            if ((mapGenerator.objects[(int) position.x][(int) (position.y + 1)] == 5) || (mapGenerator.objects[(int) position.x][(int) (position.y + 1)] == 6))
                canWalkUp = true;
            else
                canWalkUp = false;
        } else {
            canWalkUp = true;
        }

        if (mapGenerator.objects[(int) position.x][(int) (position.y - 1)] != 0) {
            if (mapGenerator.objects[(int) position.x][(int) (position.y - 1)] == 5)
                canWalkDown = true;
            else
                canWalkDown = false;
        } else {
            canWalkDown = true;
        }

        if (mapGenerator.objects[(int) position.x + 1][(int) (position.y)] != 0) {
            if (mapGenerator.objects[(int) position.x + 1][(int) (position.y)] == 5)
                canWalkRight = true;
            else
                canWalkRight = false;
        } else {
            canWalkRight = true;
        }

        if (mapGenerator.objects[(int) position.x - 1][(int) (position.y)] != 0) {
            if (mapGenerator.objects[(int) position.x - 1][(int) (position.y)] == 5)
                canWalkLeft = true;
            else
                canWalkLeft = false;
        } else {
            canWalkLeft = true;
        }


    }


    public void dealDamage() throws InterruptedException {

        float delay = 0.5f;


        switch (currentDirection) {
            case UP: {
                if (mapGenerator.natureObjects[(int) position.x][(int) position.y + 1] != null) {
                    mapGenerator.natureObjects[(int) position.x][(int) position.y + 1].getDamage(damage);
                    if (mapGenerator.natureObjects[(int) position.x][(int) position.y + 1].currentState == NatureObjects.NatureObjectStates.DEAD)
                        mapGenerator.objects[(int) position.x][(int) position.y + 1] = 6;
                }
                break;
            }
            case DOWN: {
                if (mapGenerator.natureObjects[(int) position.x][(int) position.y - 1] != null) {
                    mapGenerator.natureObjects[(int) position.x][(int) position.y - 1].getDamage(damage);
                    if (mapGenerator.natureObjects[(int) position.x][(int) position.y - 1].currentState == NatureObjects.NatureObjectStates.DEAD)
                        mapGenerator.objects[(int) position.x][(int) position.y - 1] = 1;
                }
                break;
            }
            case RIGHT: {
                if (mapGenerator.natureObjects[(int) position.x + 1][(int) position.y] != null) {
                    mapGenerator.natureObjects[(int) position.x + 1][(int) position.y].getDamage(damage);
                    if (mapGenerator.natureObjects[(int) position.x + 1][(int) position.y].currentState == NatureObjects.NatureObjectStates.DEAD)
                        mapGenerator.objects[(int) position.x + 1][(int) position.y] = 1;
                }
                break;
            }
            case LEFT: {
                if (mapGenerator.natureObjects[(int) position.x - 1][(int) position.y] != null) {
                    mapGenerator.natureObjects[(int) position.x - 1][(int) position.y].getDamage(damage);
                    if (mapGenerator.natureObjects[(int) position.x - 1][(int) position.y].currentState == NatureObjects.NatureObjectStates.DEAD)
                        mapGenerator.objects[(int) position.x - 1][(int) position.y] = 1;
                }
                break;
            }
        }

    }

}
