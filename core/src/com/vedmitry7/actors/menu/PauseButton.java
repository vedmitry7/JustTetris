package com.vedmitry7.actors.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.vedmitry7.enums.GameState;
import com.vedmitry7.utils.GameManager;

public class PauseButton extends ImageButton {

    TextureAtlas textureAtlas;
    Skin skin;
    Texture texture;
    Drawable drawable;

    public PauseButton(Drawable imageUp) {
        super(imageUp);
        setBounds(0,0,60,60);
        setPosition(480/2-15,960-15);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("eeeeeeeeeeaaaaaaahhh");
                return true;
            }
        });
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println("act");
        if (GameManager.getGameState() == GameState.PAUSED) {
            remove();
        }
        else reset();
    }
}
