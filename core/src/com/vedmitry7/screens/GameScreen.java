package com.vedmitry7.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vedmitry7.stages.GameStage;
import com.vedmitry7.utils.Constants;


public class GameScreen implements Screen {


    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;

    private GameStage stage;
    private Viewport viewport;

    private OrthographicCamera camera;


    public GameScreen() {
        camera = new OrthographicCamera();
        camera.position.set(new Vector3(VIEWPORT_WIDTH/2,VIEWPORT_HEIGHT/2,0));
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT,camera);
        stage = new GameStage(viewport);
    }

    @Override
    public void render(float delta) {
        //Clear the screen

        Gdx.gl.glClearColor(0, 0, 0, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Update the stage
        stage.draw();
        stage.act(delta);
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
