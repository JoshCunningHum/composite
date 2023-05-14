package com.kenshin.Tower;

import com.badlogic.gdx.graphics.Texture;

public class Hexa extends Tower{
    public static Texture texture = new Texture("towers/hexa-icon.png");
    public static float cost = 40;

    public Hexa() {
        super(texture);
    }
}
