package com.vedmitry7.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.vedmitry7.utils.Constants.BLOCK_SIZE;
import static com.vedmitry7.utils.Constants.GAME_FIELD_HEIGHT;
import static com.vedmitry7.utils.Constants.GAME_FIELD_WIDTH;


public class LineDrawer extends Actor {

    ShapeRenderer shapeDebugger;


    public LineDrawer() {
        shapeDebugger = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        shapeDebugger.begin(ShapeRenderer.ShapeType.Line);
        shapeDebugger.setColor(1, 1, 1, 1);
        shapeDebugger.rect(0, 0, GAME_FIELD_WIDTH, GAME_FIELD_HEIGHT);
        shapeDebugger.rect(0, 0, BLOCK_SIZE, BLOCK_SIZE);
        shapeDebugger.end();
    }
}
