package com.kenshin.Map;

import com.kenshin.Util;
import java.util.ArrayList;

public class AsciiMap {
    public static final char SITE = '_',
            PATH = '+',
            BASE = 'O',
            SPAWN = '*',
            NULL = 'N';

    String data;
    int w, h, path_count = 0;

    public static Block blockFromType(char type){
        switch (type){
            case SITE:
                return new Site();
            case PATH:
                return new Path();
            case BASE:
                return new Base();
            case SPAWN:
                return new Spawner();
            default:
                return null;
        }
    }

    public AsciiMap(int width, int height) {
        w = width;
        h = height;
        // generate empty ascii map
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w * h; i++) sb.append(SITE);
        data = sb.toString();
    }

    public AsciiMap(String data, int width, int height) {
        w = width;
        h = height;
        this.data = data;
    }

    public boolean isWithin(int x, int y) {
        return Util.isInBound(0, w-1, x) && Util.isInBound(0, h-1, y);
    }

    public char getAt(int x, int y) {
        if (!isWithin(x, y)) return NULL;
        return data.charAt(y * w + x);
    }

    public char getAt(PathPoint p) {
        return getAt(p.x, p.y);
    }

    public void setAt(int x, int y, char value) {
        if(value == PATH && getAt(x, y) != PATH) path_count++;
        StringBuilder sb = new StringBuilder(data);
        sb.setCharAt(y * w + x, value);
        data = sb.toString();
    }

    public void setAt(PathPoint p, char value) {
        setAt(p.x, p.y, value);
    }

    public boolean hasPath(int x, int y) {
        return getAt(x, y) == PATH || getAt(x, y) == SPAWN;
    }

    public boolean hasPath(PathPoint p) {
        return hasPath(p.x, p.y);
    }

    public boolean isGenSafe(int x, int y) {
        return isWithin(x, y) && !hasPath(x, y);
    }

    public boolean isGenSafe(PathPoint p) {
        return isGenSafe(p.x, p.y);
    }

    public void print() {
        System.out.println("Displaying Map: ");
        for(int i = 0; i < w; i++) System.out.print(i);
        for (int i = 0; i < h; i++) {
            System.out.println();
            for (int j = 0; j < w; j++) {
                System.out.print(data.charAt(i * w + j));
            }
            System.out.print(i);
        }
        System.out.println();
    }

    public ArrayList<Block> generate_blocks(){
        ArrayList<Block> blocks = new ArrayList<>();
        boolean isPortrait = Util.screenX < Util.screenY;
        float blockSize = (float) (isPortrait ? Util.screenX : Util.screenY) / (isPortrait ? w : h);
        Block.size = blockSize;

        // loop through the string data
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                char c = data.charAt(i * w + j);

                Block b = blockFromType(c);
                if(b == null) continue;

                b.gridX = j;
                b.gridY = h - i - 1;
                b.setPosition(j * blockSize, b.gridY * blockSize);
                b.d().setSize(blockSize, blockSize);
                b.setSize(blockSize, blockSize);

                blocks.add(b);
            }
        }

        return blocks;
    }
}
