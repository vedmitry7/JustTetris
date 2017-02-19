package com.vedmitry7.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vedmitry7.enums.GameState;
import com.vedmitry7.stages.GameStage;
import com.vedmitry7.stages.MenuStage;
import com.vedmitry7.utils.AssetsManager;
import com.vedmitry7.utils.Constants;
import com.vedmitry7.utils.GameManager;


public class GameScreen implements Screen {


    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private Stage gameStage, menuStage;


    private Viewport viewport;

    private OrthographicCamera camera;


    public GameScreen() {
        AssetsManager.loadAssets();
        camera = new OrthographicCamera();
        camera.position.set(new Vector3(VIEWPORT_WIDTH/2, VIEWPORT_HEIGHT/2,0));
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT,camera);
        gameStage = new GameStage(viewport);
        menuStage = new MenuStage(viewport);
    }

    @Override
    public void render(float delta) {
        //Clear the screen

        Gdx.gl.glClearColor(1, 1, 1, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        if(GameManager.getGameState() == GameState.MENU){
            menuStage.draw();
            Gdx.input.setInputProcessor(menuStage);
        }
        else {
            gameStage.draw();
            gameStage.act(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
       viewport.update(width,height);
        //stage.resize();
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
