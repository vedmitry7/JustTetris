package com.vedmitry7.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.vedmitry7.utils.Constants;

import java.util.ArrayList;
import java.util.Random;

import static com.vedmitry7.utils.Constants.DROPING_TIME;
import static com.vedmitry7.utils.Constants.INC_STEP_TIME;
import static com.vedmitry7.utils.Constants.MAX_STEP_TIME;
import static com.vedmitry7.utils.Constants.MIN_STEP_TIME;


public class Figure extends Actor {

    private boolean gameOver;
    private boolean droping;
    private float currentTimeToFrame;
    private float currentFrameTime;
    private float frameTime = MAX_STEP_TIME;
    private Field field;
    private BlockDrawer blockDrawer;
    private Random random = new Random();
    private ArrayList<Block> figure = new ArrayList<Block>();
    private ArrayList<Block> figureShade;
    private int[][] shape = new int[4][4];
    private int nextShape;
    private int type, size, color;

    private int x, y; // starting left up corner

    public Figure(Field f, BlockDrawer bd) {
        // System.out.println("constructor");
        this.field = f;
        this.blockDrawer = bd;
        nextShape = random.nextInt(Constants.SHAPES.length);
        nextFigure();
        currentTimeToFrame = 0;
    }

    public int getNextShape() {
        return nextShape;
    }

    public boolean isDroping() {
        return droping;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private int filledLinesCount;

    private int speed = 1;
    public int getSpeed() {
        return speed;
    }

    private void nextFigure() {
        x = 4;
        y = 0;
        figure = new ArrayList<Block>();
        type = nextShape;
        nextShape = random.nextInt(Constants.SHAPES.length);
        size = Constants.SHAPES[type][4][0];
        color = Constants.SHAPES[type][4][1];
        if (size == 4) y = -2;
        for (int i = 0; i < size; i++)
            System.arraycopy(Constants.SHAPES[type][i], 0, shape[i], 0, Constants.SHAPES[type][i].length);
        createFromShape();
    }

    synchronized void createFromShape() {
        for (int x = 0; x < size; x++)
            for (int y = 0; y < size; y++)
                if (shape[y][x] == 1) {
                    figure.add(new Block(x + this.x, y + this.y));
                }
        defineShade();
    }

    synchronized void defineShade() {
        figureShade = new ArrayList<Block>();
        for (int i = 0; i < figure.size(); i++) {
            figureShade.add(new Block(figure.get(i).getX(), figure.get(i).getY()));
        }
        while (!isTouchGroundShade()) {
            stepDownShade();
        }
    }

    boolean isCrossGround() {
        for (Block block : figure)
            if (!field.checkAvailability(block.getX(), block.getY())) return true;
        return false;
    }

    public void stepDown() {
        for (Block block : figure) block.setY(block.getY() + 1);
        y++;

    }

    void stepDownShade() {
        for (Block block : figureShade) block.setY(block.getY() + 1);
    }

    public boolean isTouchGround() {
        for (Block block : figure)
            if (!field.checkAvailability(block.getY() + 1, block.getX()))
                return true;
        return false;
    }

    public void drop() {
        if (droping)
            return;
        currentFrameTime = frameTime;
        frameTime = DROPING_TIME;
        droping = true;
    }

    boolean isTouchGroundShade() {
        for (Block block : figureShade)
            if (!field.checkAvailability(block.getY() + 1, block.getX()))
                return true;
        return false;
    }

    void leaveOnTheGround() {
        for (Block block : figure) field.getMatrix()[block.getY()][block.getX()] = color;
        if (droping) {
            droping = false;
            frameTime = currentFrameTime;
        }
    }

    public void move(int direction) {
        if (droping)
            return;
        if (!isTouchWall(direction)) {
            for (Block block : figure
                    ) {
                block.setX(block.getX() + direction);
            }
            x += direction;
        }
        defineShade();
    }

    boolean isTouchWall(int direction) {
        for (Block block : figure
                ) {
            if (!field.checkAvailability(block.getY(), block.getX() + direction))
                return true;
        }
        return false;
    }

    synchronized public void rotate() {
        if (droping || type == 1)
            return;
        for (int i = 0; i < size / 2; i++)
            for (int j = i; j < size - 1 - i; j++) {
                int tmp = shape[size - 1 - j][i];
                shape[size - 1 - j][i] = shape[size - 1 - i][size - 1 - j];
                shape[size - 1 - i][size - 1 - j] = shape[j][size - 1 - i];
                shape[j][size - 1 - i] = shape[i][j];
                shape[i][j] = tmp;
            }
        figure.clear();
        createFromShape();
        for (Block block : figure
                ) {
            if (!field.checkAvailability(block.getY(), block.getX())) {
                for (int i = 0; i < size / 2; i++)
                    for (int j = i; j < size - 1 - i; j++) { // counterclockwise
                        int tmp = shape[i][j];
                        shape[i][j] = shape[j][size - 1 - i];
                        shape[j][size - 1 - i] = shape[size - 1 - i][size - 1 - j];
                        shape[size - 1 - i][size - 1 - j] = shape[size - 1 - j][i];
                        shape[size - 1 - j][i] = tmp;
                    }
                figure.clear();
                createFromShape();
                break;
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        currentTimeToFrame += delta;
        if (currentTimeToFrame > frameTime) {
            if (this.isTouchGround()) {
                this.leaveOnTheGround();
                filledLinesCount += field.checkLines();
                this.nextFigure();
                if (field.matrix[1][4] != 0 || field.matrix[1][5] != 0) {
                    gameOver = true;
                    System.out.println("GAME OVER !!!!!!!!!!!!");
                }
            }
            this.stepDown();
            currentTimeToFrame = 0;
        }

        if (filledLinesCount >= 10) {
            if (speed < 20)
                speed += 1;
            incSpeed();
            filledLinesCount -= 10;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        for (Block block : figure)
            blockDrawer.draw(batch, block.getX(), block.getY(), color, false);
        for (Block block : figureShade)
            blockDrawer.draw(batch, block.getX(), block.getY(), color, true);
    }

    private void incSpeed() {
        frameTime -= INC_STEP_TIME;
        if (frameTime < MIN_STEP_TIME)
            frameTime = MIN_STEP_TIME;
    }

    class Block {
        private int x, y;


        public Block(int x, int y) {
            setX(x);
            setY(y);
        }
        void setX(int x) { this.x = x; }

        void setY(int y) { this.y = y; }

        int getX() { return x; }
        int getY() { return y; }

    }

}
