package com.kenshin.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class GameObject extends Group {
    // we might have to add an actor compound to display itself
    private Image _draw;
    boolean isDrawable = false;

    public GameObject(){

    }

    public GameObject(Texture t){
        _draw = new Image(t);
        isDrawable = true;
        this.addActor(_draw);
    }

    public Image d(){
        return _draw;
    }


}
