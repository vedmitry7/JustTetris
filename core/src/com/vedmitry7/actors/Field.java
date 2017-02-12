package com.vedmitry7.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vedmitry7.utils.Constants.PREFERENCE_NAME;


public class Field extends Actor {
    private final int fieldWidth = 10;

    private final int fieldHeight = 20;
    int matrix[][] = new int[fieldHeight][fieldWidth];

    private List<int[]> lines;

    private int score;
    private int filledLines;

    BlockDrawer blockDrawer;

    public Field(BlockDrawer blockDrawer, boolean newGame) {
        this.blockDrawer = blockDrawer;
        lines = new ArrayList<int[]>();

        if(newGame)
            for (int i = 0; i < fieldHeight; i++) {
                Arrays.fill(matrix[i], 0);
            }
        else
            fillFromPrefs();
        matrix[19][9] = 101;
        matrix[0][0] = 101;
    }

    int getScore() {
        return score;
    }


    int[][] getMatrix() {
        return matrix;
    }

    public void setValue(int x,int y, int value) {
        if(x>-1 && x < fieldHeight && y > -1 && y < fieldWidth)
            matrix[x][y] = value;
    }

    int checkLines(){
        ArrayList<int[]> lines = new ArrayList<int[]>();
        boolean b = false;

        for (int i = 0; i < fieldHeight; i++) {
            int count = 0;
            for (int j = 0; j < fieldWidth; j++) {
                if(matrix[i][j] != 0)
                    count++;
            }
            if(count!= fieldWidth){
                lines.add(matrix[i]);
            }
        }

        filledLines = fieldHeight - lines.size();
        switch (filledLines){
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 700;
                break;
            case 4:
                score += 1500;
                break;
        }

        while (lines.size()< fieldHeight){
            lines.add(0, new int[fieldWidth]);
        }
        matrix = lines.toArray(new int[fieldHeight][fieldWidth]);

        if(filledLines > 0) Gdx.input.vibrate(50);
        return filledLines;
    }

    boolean checkAvailability(int x, int y){
        if(x < fieldHeight && y < fieldWidth && y > -1 && x > -1) {
            if (matrix[x][y] == 0)
                return true;
        }
        return false;
    }


    private void fillFromPrefs(){
        Preferences prefs = Gdx.app.getPreferences("My Preferences");
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                matrix[i][j] = prefs.getInteger(String.valueOf(i)+String.valueOf(j),0);
                System.out.println("fillFromPrefs." + i + "," + j + " = " + matrix[i][j]);
            }
        }
    }

    void fillPrefs(){
        Preferences prefs = Gdx.app.getPreferences(PREFERENCE_NAME);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                map.put(String.valueOf(i)+String.valueOf(j), matrix[i][j]);
                System.out.println(String.valueOf(i)+String.valueOf(j) + " - " + matrix[i][j]);
            }
        }
        prefs.put(map);
        prefs.flush();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                if(matrix[i][j] != 0)
                    blockDrawer.draw(batch, j, i, matrix[i][j], false);
            }
        }
    }
}
