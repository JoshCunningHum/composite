package com.kenshin.Menu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kenshin.Game;
import com.kenshin.GameObject.GameObject;
import com.kenshin.Map.Map;
import com.kenshin.Menu.Dialogs.TowerBuilderDialog;
import com.kenshin.Menu.UI.LabelIcon;
import com.kenshin.Tower.Tower;
import com.kenshin.Util;

import jdk.javadoc.internal.doclets.formats.html.Table;

public class GameMenu extends Menu {
    Map map;

    // Layers
    Group towers, enemies, projectiles;

    public GameMenu(Game g){
        super(g);
    }

    // accessible labels for modification through runtime
    Label lblHealth, lblMoney;

    // accessible dialogs
    public Dialog dTowerBuilder, dTowerManager, dModManager, dModApplyer;

    public void _init(){

        Skin siren = Util.Theme.Siren();

        map = new Map(_g.config.map);
        map.setY((Util.screenY - map.getHeight()) / 2);

        addActor(map);

        towers = new Group();
        enemies = new Group();
        projectiles = new Group();

        lblHealth = new Label("0", siren, "description");
        Image health_img = new Image(Util.UI.Textures.ico_heart);
        LabelIcon health = new LabelIcon(health_img, lblHealth);
        health.setY(getHeight() - health_img.getHeight());

        addActor(health);

        lblMoney = new Label("0", siren, "description");
        Image money_img = new Image(Util.UI.Textures.ico_gold);
        LabelIcon money = new LabelIcon(money_img, lblMoney);
        money.setPosition(getWidth() / 2 - money_img.getWidth(), getHeight() - money_img.getHeight());

        addActor(money);

        // Initialize Dialogs
        dTowerBuilder = new TowerBuilderDialog(siren);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void exit() {
        super.exit();
    }

    public void showDialogs(Dialog d, Action a){
        d.show(this, a);
    }

    public void addTower(Tower t){

    }
}
