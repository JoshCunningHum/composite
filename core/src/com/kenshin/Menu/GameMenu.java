package com.kenshin.Menu;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kenshin.Game;
import com.kenshin.Map.Block;
import com.kenshin.Map.Map;
import com.kenshin.Map.Site;
import com.kenshin.Menu.Dialogs.TowerBuilderDialog;
import com.kenshin.Menu.Dialogs.TowerManagerDialog;
import com.kenshin.Menu.UI.LabelIcon;
import com.kenshin.Tower.Tower;
import com.kenshin.Util;

import java.awt.Rectangle;


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

    private Site selectedSite = null;
    Skin siren;

    public void _init(){

        siren = Util.Theme.Siren();

        map = new Map(_g.config.map);
        map.setY((Util.screenY - map.getHeight()) / 2);

        addActor(map);

        towers = new Group();
        enemies = new Group();
        projectiles = new Group();

        towers.setY(map.getY());
        enemies.setY(map.getY());
        projectiles.setY(map.getY());

        addActor(towers);
        addActor(enemies);
        addActor(projectiles);

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
        dTowerManager = new TowerManagerDialog(siren);

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void exit() {
        super.exit();
    }

    public void showDialogs(Dialog d){
        d.show(this);
    }

    public void clearSelected(){

        for(Block b : map.blocks){
            if(!(b instanceof Site)) continue;
            b.d().setDrawable(Util.Theme.Siren().getDrawable("site"));
        }

    }

    public void setSelectedSite(Site selectedSite) {

        this.selectedSite = selectedSite;
        clearSelected();
        if(selectedSite == null) return;

        selectedSite.d().setDrawable(Util.Theme.Siren().getDrawable("site-selected"));
    }

    InputListener closeOnClickOutside;

    public void _removeTempListeners(){
        if(closeOnClickOutside != null) this.removeListener(closeOnClickOutside);
    }

    public void showDialogs(final Dialog d, Action a){
        d.show(this);
        this.addAction(a);

        System.out.println(d.getX() + " " + d.getY() + " " + d.getWidth() + " " + d.getHeight());
        closeOnClickOutside = new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!(new Rectangle((int) d.getX(), (int) d.getY(), (int) d.getWidth(), (int) d.getHeight())).contains(x, y)) d.hide();
                return true;
            }
        };

        this.addListener(closeOnClickOutside);
    }

    public void addTower(Tower t){
        if(t == null) return;
        towers.addActor(t);

        float dX = selectedSite.getX(),
              dY = selectedSite.getY();

        t.d().setSize(Block.size * 0.75f, Block.size * 0.75f);
        t.d().setPosition(Block.size * 0.125f, Block.size * 0.125f);
        t.setBounds(dX, dY, Block.size, Block.size);
    }
}
