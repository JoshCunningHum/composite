package com.kenshin.Tower;

import com.badlogic.gdx.graphics.Texture;

public class Penta extends Tower{
    public static Texture texture = new Texture("towers/penta-icon.png");
    public static float cost = 45;

    public Penta() {
        super(texture);
    }
}
