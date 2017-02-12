package com.vedmitry7.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PauseButton extends Button {

    TextureAtlas textureAtlas;
    Skin skin;
    Texture texture;
    Drawable drawable;

    public PauseButton() {
        texture = new Texture(Gdx.files.internal("pause_button.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        loadTextureRegion();
        this.setPosition(225f, 962.5f);
    }

    protected void loadTextureRegion() {
        ButtonStyle style = new ButtonStyle();
        style.up = drawable;
        setStyle(style);
    }
}
