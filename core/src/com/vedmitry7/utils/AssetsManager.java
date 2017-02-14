package com.vedmitry7.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;


public class AssetsManager {

    private static TextureAtlas textureAtlas;
    private static HashMap<Integer, TextureRegion> texturesMap = new HashMap<Integer, TextureRegion>();
    private AssetsManager() {
    }

    public static TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public static HashMap<Integer, TextureRegion> getTexturesMap() {
        return texturesMap;
    }

    public static void loadAssets() {
        textureAtlas = new TextureAtlas("Main_menu.pack");

        texturesMap.put(0, new TextureRegion(new Texture(Gdx.files.internal("blocks/i.png"))));
        texturesMap.put(1, new TextureRegion(new Texture(Gdx.files.internal("blocks/o.png"))));
        texturesMap.put(2, new TextureRegion(new Texture(Gdx.files.internal("blocks/j.png"))));
        texturesMap.put(3, new TextureRegion(new Texture(Gdx.files.internal("blocks/L.png"))));
        texturesMap.put(4, new TextureRegion(new Texture(Gdx.files.internal("blocks/s.png"))));
        texturesMap.put(5, new TextureRegion(new Texture(Gdx.files.internal("blocks/t.png"))));
        texturesMap.put(6, new TextureRegion(new Texture(Gdx.files.internal("blocks/z.png"))));
    }
}
