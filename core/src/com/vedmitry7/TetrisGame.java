package com.vedmitry7;

import com.badlogic.gdx.Game;
import com.vedmitry7.screens.GameScreen;

public class TetrisGame extends Game {

	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose () {

	}
}
