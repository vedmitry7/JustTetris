package com.vedmitry7.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class AssetsManager {

    private static TextureAtlas textureAtlas;
    private AssetsManager() {
    }

    public static TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public static void loadAssets() {
        textureAtlas = new TextureAtlas("main_menu.pack");

    }
}
