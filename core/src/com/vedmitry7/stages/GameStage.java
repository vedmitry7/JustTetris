package com.vedmitry7.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private OrthographicCamera camera;

    public GameStage(Viewport v) {
        super();
        this.setViewport(v);
      //  setUpCamera();
        initGameObject();
        initButton();

    }


    private void initButton() {

        Texture texture = new Texture(Gdx.files.internal("pause_button.png"));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));

        pauseButton = new PauseButton(drawable);
        pauseButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(GameManager.getGameState()== GameState.PAUSED)
                    GameManager.setGameState(GameState.RUNNING);
                else
                    GameManager.setGameState(GameState.PAUSED);
                return true;
            }
        });
        addActor(pauseButton);

        region = new TextureRegion(texture);

      /*  ImageButton button = new ImageButton(drawable);
        button.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(GameManager.getGameState()==GameState.PAUSED)
                    GameManager.setGameState(GameState.RUNNING);
                else
                GameManager.setGameState(GameState.PAUSED);
                return true;
            }
        });
        button.setBounds(0,0,60,60);
        button.setPosition(480/2-15,960-15);
        addActor(button);*/

        skin = new Skin();
        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);

        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();

        skin.add("default",bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton textButton=new TextButton("PLAY",textButtonStyle);
        textButton.setPosition(200, 200);
        addActor(textButton);

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
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
