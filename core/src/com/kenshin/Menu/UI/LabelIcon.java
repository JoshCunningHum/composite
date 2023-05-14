package com.kenshin.Menu.UI;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kenshin.GameObject.GameObject;

public class LabelIcon extends GameObject {

    public Image icon;
    public Label label;

    public LabelIcon(Image icon, Label label){
        this.label = label;
        this.icon = icon;
        addActor(icon);
        addActor(label);

        icon.setBounds(0, 0, label.getHeight() * 3 / 2, label.getHeight() * 3 / 2);

        label.setX(icon.getRight());
        label.setY(icon.getHeight() / 2, Align.center);
    }

    public void setText(String text){
        label.setText(text);
    }
}
