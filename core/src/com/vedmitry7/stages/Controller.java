package com.vedmitry7.stages;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.vedmitry7.actors.menu.Figure;
import com.vedmitry7.utils.Constants;


class Controller implements InputProcessor {

    private boolean droping, wasDragged;

   private Figure figure;
   private int touchUpGor, touchDownGor, touchUpVert, touchDownVert;

    public Controller(Figure figure) {
        this.figure = figure;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.DOWN)
            figure.drop();
        if(keycode == Input.Keys.LEFT)
            figure.move(Constants.LEFT);
        if(keycode == Input.Keys.RIGHT)
            figure.move(Constants.RIGHT);
        if(keycode == Input.Keys.UP)
            figure.rotate();
        if(keycode == Input.Keys.SPACE)
            System.out.println(figure.isGameOver());

            return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchDownGor = screenX;
        touchDownVert = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchUpGor = screenX;
        touchUpVert = screenY;
        System.out.println("                                    " + (touchUpVert-touchDownVert));
        if(!wasDragged) {
            if (touchDownVert - touchUpVert > 54)
                figure.rotate();
            if (touchDownVert - touchUpVert < -54)
                figure.drop();
        }
        wasDragged = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        touchUpGor = screenX;
        if(move())
            touchDownGor = screenX;
        System.out.println("touchDragged " + screenX);

        return false;
    }
    private boolean move(){
        if(touchUpGor-touchDownGor>54){
            figure.move(Constants.RIGHT);
            wasDragged = true;
            return true;
        }
        if(touchUpGor-touchDownGor<-54) {
            figure.move(Constants.LEFT);
            wasDragged = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
