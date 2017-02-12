package com.vedmitry7.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.vedmitry7.utils.Constants.GAME_FIELD_HEIGHT;
import static com.vedmitry7.utils.Constants.GAME_FIELD_WIDTH;


public class GameInfo extends Actor {
    private Field field;
    private Figure figure;
    private BitmapFont font;

    public GameInfo(Field field, Figure figure) {
        this.field = field;
        this.figure = figure;
        initFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, "Score: " + field.getScore(), 2.5f, GAME_FIELD_HEIGHT + 20f);
        font.draw(batch, "Speed: " + figure.getSpeed(), GAME_FIELD_WIDTH/3.5f, GAME_FIELD_HEIGHT + 20f);
    }

    private void initFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/aavantebs_italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        font = generator.generateFont(parameter);
    }
}
