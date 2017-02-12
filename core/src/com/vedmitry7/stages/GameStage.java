package com.vedmitry7.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vedmitry7.actors.menu.BlockDrawer;
import com.vedmitry7.actors.menu.Field;
import com.vedmitry7.actors.menu.Figure;
import com.vedmitry7.utils.Constants;


public class GameStage extends Stage {

    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    ImageButton button;
    TextureAtlas textureAtlas;
    Skin skin;
    Figure figure;
    Field gameField;
    BlockDrawer blockDraver;
    Viewport viewport;
    BitmapFont font;

    private OrthographicCamera camera;

    public GameStage(Viewport v) {
        super();
        this.setViewport(v);
      //  setUpCamera();
        initGameObject();
        initButton();
        initInfo();
    }

    private void initInfo() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/magneto-bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 75;
        font = generator.generateFont(parameter);
    }

    private void initButton() {

    }

    private void initGameObject() {
        blockDraver = new BlockDrawer();
        gameField = new Field(blockDraver, true);
        figure = new Figure(gameField, blockDraver);

        Gdx.input.setInputProcessor(new Controller(figure));

        addActor(gameField);
        addActor(figure);

    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void draw() {
        super.draw();
        this.getBatch().begin();
        font.draw(this.getBatch(),"Score: 0",5,250);
        this.getBatch().end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void resize() {
    }
}
