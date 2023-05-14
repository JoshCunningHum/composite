package com.kenshin.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kenshin.Game;
import com.kenshin.Util;

abstract public class Menu extends Stage {

    private boolean _dShowGrid = false;
    private int _dGridCols = 10, _dGridRows = 10;
    protected Game _g;

    ShapeRenderer debug;

    public Menu(Game g) {
        super(Util.default_screen);
        _g = g;

        _init();
    }

    abstract public void _init();

    // Like animation on show etc
    public void show(){
        debug = new ShapeRenderer();
    }

    public void exit(){
        debug.dispose();
    }

    @Override
    public void draw() {
        super.draw();
        if(_dShowGrid){
            float x = getWidth(), y = getHeight(), gapX = x / _dGridCols, gapY = y / _dGridRows;

            debug.setAutoShapeType(true);
            debug.begin();
            debug.setColor(Color.CYAN);
            for(int i = 0; i < y; i++){
                float cX = i * gapX;
                for(int j = 0; j < x; j++){
                    float cY = i * gapY;
                    debug.line(cX, 0, cX, y);
                    debug.line(0, cY, x, cY);
                }
            }
            debug.end();
        }
    }

    public GridPoint2 getCenter(){
        return new GridPoint2((int) getWidth() / 2, (int) getHeight() / 2);
    }

    public GridPoint2 getCenter(GridPoint2 offset){
        return getCenter().add(offset);
    }

    public void debug_showGrid(){
        debug_showGrid(true);
    }

    public void debug_showGrid(boolean state){
        debug_showGrid(10, 10, state);
    }

    public void debug_showGrid(int rows, int cols, boolean state){
        _dShowGrid = state;
        _dGridCols = cols;
        _dGridRows = rows;
    }
}
