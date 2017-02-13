package com.vedmitry7.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vedmitry7.actors.BlockDrawer;
import com.vedmitry7.actors.Field;
import com.vedmitry7.actors.Figure;
import com.vedmitry7.actors.GameInfo;
import com.vedmitry7.actors.LineDrawer;
import com.vedmitry7.actors.menu.PauseButton;
import com.vedmitry7.utils.Constants;


public class GameStage extends Stage {

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    Figure figure;
    Field gameField;
    BlockDrawer blockDraver;
    TextureRegion region;

    private OrthographicCamera camera;

    public GameStage(Viewport v) {
        super();
        this.setViewport(v);
      //  setUpCamera();
        initGameObject();
        initButton();

    }


    private void initButton() {
        PauseButton pauseButton = new PauseButton();
        addActor(pauseButton);

        Texture texture = new Texture(Gdx.files.internal("pause_button.png"));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        region = new TextureRegion(texture);
        ImageButton button = new ImageButton(drawable);
        button.setBounds(0,0,60,60);
        button.setPosition(480/2-15,960-15);
        addActor(button);
    }

    private void initGameObject() {
        blockDraver = new BlockDrawer();
        gameField = new Field(blockDraver, true);
        figure = new Figure(gameField, blockDraver);

        Gdx.input.setInputProcessor(new Controller(figure));

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
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
