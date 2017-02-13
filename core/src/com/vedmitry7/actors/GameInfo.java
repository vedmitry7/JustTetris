package com.vedmitry7.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vedmitry7.utils.AssetsManager;

import java.util.HashMap;

import static com.vedmitry7.utils.Constants.GAME_FIELD_HEIGHT;
import static com.vedmitry7.utils.Constants.GAME_FIELD_WIDTH;


public class GameInfo extends Actor {
    private Field field;
    private Figure figure;
    private BitmapFont font;
    private static HashMap<Integer, TextureRegion> texturesMap;
    private TextureRegion region;
    private Sprite sprite;

    public GameInfo(Field field, Figure figure) {
        this.field = field;
        this.figure = figure;
        texturesMap = AssetsManager.getTexturesMap();
        initFont();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        font.draw(batch, "Score: " + field.getScore(), 2.5f, GAME_FIELD_HEIGHT + 20f);
        font.draw(batch, "Speed: " + figure.getSpeed(), GAME_FIELD_WIDTH/3.5f, GAME_FIELD_HEIGHT + 20f);
        font.draw(batch, "Next: ", GAME_FIELD_WIDTH-140, GAME_FIELD_HEIGHT + 20f);
        sprite = new Sprite(texturesMap.get(figure.getNextShape()));
        sprite.setPosition(GAME_FIELD_WIDTH-60,GAME_FIELD_HEIGHT);
        sprite.setSize(45,30);
        sprite.draw(batch);
    }

    private void initFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/aavantebs_italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
    }
}
