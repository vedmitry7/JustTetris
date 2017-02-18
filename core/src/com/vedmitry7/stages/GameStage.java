package com.vedmitry7.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vedmitry7.actors.BlockDrawer;
import com.vedmitry7.actors.Field;
import com.vedmitry7.actors.Figure;
import com.vedmitry7.actors.GameInfo;
import com.vedmitry7.actors.LineDrawer;
import com.vedmitry7.actors.menu.ButtonManager;
import com.vedmitry7.actors.menu.PauseButton;
import com.vedmitry7.enums.GameState;
import com.vedmitry7.utils.Constants;
import com.vedmitry7.utils.GameManager;


public class GameStage extends Stage {

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private int touchUpGor, touchDownGor, touchUpVert, touchDownVert;
    private boolean wasDragged;

    Figure figure;
    Field gameField;
    BlockDrawer blockDraver;
    TextureRegion region;
    PauseButton pauseButton;
    Skin skin;
    TextButton continueButton, exitButton, menuButton;
    Texture texture;
    private OrthographicCamera camera;

    public GameStage(Viewport v) {
        super();
        this.setViewport(v);
        initGameObject();
        initButton();
        setMenuVisible(false);
       // initBackground();
    }

    private void initBackground() {
        texture = new Texture(Gdx.files.internal("bg_white.png"));
        skin = new Skin();
        skin.add("bg", texture);

    }


    private void initButton() {

        texture = new Texture(Gdx.files.internal("pause_button.png"));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));

        pauseButton = new PauseButton(drawable);
        pauseButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(GameManager.getGameState()== GameState.PAUSED)
                    GameManager.setGameState(GameState.RUNNING);
                else {
                    GameManager.setGameState(GameState.PAUSED);
                    setMenuVisible(true);
                    pauseButton.setVisible(false);
                }
                return true;
            }
        });
        addActor(pauseButton);

        region = new TextureRegion(texture);

        continueButton = ButtonManager.getTextButton("Continue");
        continueButton.setSize(150,75);
        continueButton.setPosition(165, 450);
        continueButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    GameManager.setGameState(GameState.RUNNING);
                    setMenuVisible(false);
                    pauseButton.setVisible(true);
                return true;
            }
        });
        addActor(continueButton);

        menuButton = ButtonManager.getTextButton("Main menu");
        menuButton.setSize(150,75);
        menuButton.setPosition(165, 350);
        menuButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                return true;
            }
        });

        addActor(menuButton);

        exitButton = ButtonManager.getTextButton("Exit");
        exitButton.setSize(150,75);
        exitButton.setPosition(165, 250);
        exitButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        addActor(exitButton);

    }

    private void setMenuVisible(boolean visible){
        continueButton.setVisible(visible);
        exitButton.setVisible(visible);
        menuButton.setVisible(visible);
    }

    private void initGameObject() {
        blockDraver = new BlockDrawer();
        gameField = new Field(blockDraver, true);
        figure = new Figure(gameField, blockDraver);


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(new Controller(figure));
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);

        GameInfo gameInfo = new GameInfo(gameField, figure);

        LineDrawer lineDrawer = new LineDrawer();
        addActor(gameField);
        addActor(figure);
        addActor(gameInfo);

       // addActor(lineDrawer);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void draw() {
      /*  getBatch().begin();
        getBatch().draw(skin.getRegion("bg"),0,0,540,990);
        getBatch().end();*/
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
}
