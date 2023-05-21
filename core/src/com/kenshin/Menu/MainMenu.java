package com.kenshin.Menu;

import static com.badlogic.gdx.scenes.scene2d.Touchable.disabled;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kenshin.Game;
import com.kenshin.Util;
import com.ray3k.stripe.scenecomposer.SceneComposerStageBuilder;

import org.w3c.dom.Text;

// Used for storing different menus for the game (start, map, settings, upgrades etc)
public class MainMenu extends Menu{

    public MainMenu(Game g){
        super(g);
    }

    @Override
    public void _init() {
        boolean isHD = Util.isHD();
        Skin skin = Util.Theme.Siren();

        SceneComposerStageBuilder builder = new SceneComposerStageBuilder();
        builder.build(this, skin, Gdx.files.internal("ui/" + (isHD ? "mainMenuHD" : "mainMenu") + ".json"));

        ((Table) getRoot().getChild(0)).defaults().prefHeight(Util.UI.PREF.buttonHeight());

        TextButton play = getRoot().findActor("playBtn");
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                _g.switchMenu(_g.gameMenu);
            }
        });

        TextButton exit = getRoot().findActor("exitBtn");
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void exit() {
        super.exit();
    }
}
