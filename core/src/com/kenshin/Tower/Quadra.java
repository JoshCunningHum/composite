package com.kenshin.Tower;

import com.badlogic.gdx.graphics.Texture;

public class Quadra extends Tower{
    public static Texture texture = new Texture("towers/quadra-icon.png");
    public static float cost = 20;

    public Quadra() {
        super(texture);
    }
}
