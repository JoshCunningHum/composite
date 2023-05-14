package com.kenshin.Map;

public class MapConfig {
    public int cols, rows;

    public MapConfig(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public void set(int c, int r){
        cols = c;
        rows = r;
    }

    public int minPath(){
        return rows * cols / 3;
    }
}
