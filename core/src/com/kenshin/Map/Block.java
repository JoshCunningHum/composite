package com.kenshin.Map;

import com.badlogic.gdx.graphics.Texture;
import com.kenshin.GameObject.GameObject;

public abstract class Block extends GameObject {

    public int gridX, gridY;
    Block(Texture t){
        super(t);
    }
}