package com.vedmitry7.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vedmitry7.actors.menu.ButtonManager;
import com.vedmitry7.enums.GameState;
import com.vedmitry7.utils.GameManager;


public class MenuStage extends Stage {

    private TextButton continueButton, exitButton, settingsButton, newGameButton;

    public MenuStage(Viewport v) {
        super();
        this.setViewport(v);
        initButton();
    }

    private void initButton() {
        continueButton = ButtonManager.getTextButton("Continue");
        continueButton.setSize(150,75);
        continueButton.setPosition(165, 450);
        continueButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameManager.setGameState(GameState.RUNNING);
                return true;
            }
        });
        addActor(continueButton);

        newGameButton = ButtonManager.getTextButton("New game");
        newGameButton.setSize(150,75);
        newGameButton.setPosition(165, 350);
        newGameButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameManager.setGameState(GameState.RUNNING);
                return true;
            }
        });
        addActor(newGameButton);

        settingsButton = ButtonManager.getTextButton("Settings");
        settingsButton.setSize(150,75);
        settingsButton.setPosition(165, 250);
        settingsButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameManager.setGameState(GameState.RUNNING);
                return true;
            }
        });
        addActor(settingsButton);

        exitButton = ButtonManager.getTextButton("Exit");
        exitButton.setSize(150,75);
        exitButton.setPosition(165, 150);
        exitButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        addActor(exitButton);
    }
}
