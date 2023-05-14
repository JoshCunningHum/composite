package com.kenshin.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Spawner extends Block{
    public static Texture texture = new Texture(Gdx.files.internal("blocks/spawner.png"));

    Spawner(){
        super(texture);
    }
}
