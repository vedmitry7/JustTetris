package com.vedmitry7.actors.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

import static com.vedmitry7.utils.Constants.BLOCK_SIZE;
import static com.vedmitry7.utils.Constants.GAME_FIELD_HEIGHT;


public class BlockDrawer {
    private Texture texture;
    private Sprite sprite;
    private float xPos;
    private float yPos;
    private float spriteSize;
   private Skin skin;
   private TextureAtlas atlas;
   private ArrayList<TextureRegion> blocks;

    public BlockDrawer() {
        atlas = new TextureAtlas("blocks.pack");
        skin = new Skin(atlas);

        blocks = new ArrayList<TextureRegion>();
        blocks.add(skin.getRegion("green_block"));
        blocks.add(skin.getRegion("red_block"));
        blocks.add(skin.getRegion("blue_block"));
        blocks.add(skin.getRegion("yellow_block"));
        blocks.add(skin.getRegion("pink_block"));
        System.out.println(blocks.size() + " Block Size");

       // texture = new Texture(Gdx.files.internal("block.png"));
        sprite = new Sprite();
        sprite.setRegion(blocks.get(2));
        spriteSize = BLOCK_SIZE;
        sprite.setSize(spriteSize,spriteSize);
    }

    void draw(Batch spriteBatch, int x, int y, int color, boolean shade){
        if(spriteSize!=BLOCK_SIZE){
            spriteSize = BLOCK_SIZE;
            sprite.setSize(spriteSize,spriteSize);
        }
        yPos = GAME_FIELD_HEIGHT  - BLOCK_SIZE  - (BLOCK_SIZE * y);
        xPos = BLOCK_SIZE * x ;
        sprite.setPosition(xPos,yPos);

        sprite.setRegion(blocks.get(color-100));

        if(shade)
            sprite.setAlpha(0.15f);
        else
            sprite.setAlpha(1);
        sprite.draw(spriteBatch);
    }
}
