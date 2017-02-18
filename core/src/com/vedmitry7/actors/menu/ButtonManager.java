package com.vedmitry7.actors.menu;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonManager {

    private static BitmapFont font;

    public static TextButton getTextButton(String buttonText){

        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(25, 25, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);

        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        initFont();
        BitmapFont bfont = font;

        skin.add("default",bfont);

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
       // textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        TextButton textButton= new TextButton(buttonText, textButtonStyle);
        //textButton.setSize(200,70);
        return textButton;
    }

    private static void initFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/aavantebs_italic.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;
        font = generator.generateFont(parameter);
        font.setColor(Color.BLACK);
    }
}
