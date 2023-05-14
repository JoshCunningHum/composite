package com.kenshin.Tower;

import com.badlogic.gdx.graphics.Texture;

public class Apiera extends Tower{
    public static Texture texture = new Texture("towers/apiera-icon.png");
    public static float cost = 50;

    public Apiera() {
        super(texture);
    }
}
