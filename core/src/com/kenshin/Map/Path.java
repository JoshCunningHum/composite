package com.kenshin.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Path extends Block{
    public int dir;
    public static Texture texture = new Texture(Gdx.files.internal("blocks/path.png"));

    Path(){
        super(texture);
    }
}
