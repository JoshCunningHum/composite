package com.kenshin.Map;

import com.badlogic.gdx.math.GridPoint2;

class PathPoint extends GridPoint2 {

    static int getDirNum(String dir) {
        try {

            switch (dir) {
                case "top":
                    return -1;
                case "bottom":
                    return 1;
                case "left":
                    return -2;
                case "right":
                    return 2;
                case "none":
                    return 0;
            }
            throw new Exception("Dir: " + dir + " is not a direction representation");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    static String getDirString(int dir) {
        try {

            switch (dir) {
                case 0:
                    return "none";
                case -1:
                    return "top";
                case 1:
                    return "bottom";
                case -2:
                    return "left";
                case 2:
                    return "right";
            }
            throw new Exception("Dir: " + dir + " is not a direction representation");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return "none";
    }

    int _dir;

    public PathPoint(int x, int y, int dir) {
        super();
        this.x = x;
        this.y = y;
        this._dir = dir;
    }

    public PathPoint(int x, int y, String dir) {
        super();
        this.x = x;
        this.y = y;
        this._dir = getDirNum(dir);
    }

    public PathPoint(PathPoint point){
        x = point.x;
        y = point.y;
        _dir = point._dir;
    }

    public String getDir() {
        return getDirString(_dir);
    }

    @Override
    public PathPoint cpy() {
        return new PathPoint(this);
    }

    @Override
    public PathPoint add(int x, int y) {
        return (PathPoint) super.add(x, y);
    }

    public PathPoint addDir(String dir) {
        switch (dir) {
            case "top":
                add(0, -1);
                break;
            case "bottom":
                add(0, 1);
                break;
            case "left":
                add(-1, 0);
                break;
            case "right":
                add(1, 0);
                break;
        }
        return this;
    }

    public PathPoint addDir(int dir) {
        return addDir(getDirString(dir));
    }
}
