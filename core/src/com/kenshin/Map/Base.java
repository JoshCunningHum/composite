package com.kenshin.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Base extends Block{
    public static Texture texture = new Texture(Gdx.files.internal("blocks/base.png"));

    Base(){
        super(texture);
    }
}
