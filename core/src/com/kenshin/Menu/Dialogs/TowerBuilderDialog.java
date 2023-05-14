package com.kenshin.Menu.Dialogs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.kenshin.GameObject.GameObject;
import com.kenshin.Menu.GameMenu;
import com.kenshin.Tower.Apiera;
import com.kenshin.Tower.Hexa;
import com.kenshin.Tower.Penta;
import com.kenshin.Tower.Quadra;
import com.kenshin.Tower.Tower;
import com.kenshin.Util;


public class TowerBuilderDialog extends Dialog {

    public static class TowerBuilderButton extends GameObject{
        public TextButton btn;
        public Image icon;

        private String _drawable;

        final float icon_pad_top = 30, icon_size = 30, updown_diff = 38;

        private float icon_up, icon_down;

        public TowerBuilderButton(TextButton btn, final String drawable){
            this.btn = btn;
            icon = new Image(btn.getSkin().getDrawable(drawable));
            _drawable = drawable;

            icon.setTouchable(Touchable.disabled);
            btn.setTouchable(Touchable.disabled);

            icon_up = btn.getHeight() - icon_pad_top;
            icon_down = icon_up - updown_diff;

            // Reposition image
            icon.setSize(icon_size, icon_size);
            icon.setPosition(btn.getWidth() / 2, icon_up, Align.center);

            addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    TextButton btn = TowerBuilderButton.this.btn;
                    Image icon = TowerBuilderButton.this.icon;
                    boolean isChecked = btn.isChecked();


                    // get all other text buttons
                    SnapshotArray<Actor> others = getParent().getChildren();
                    for(Actor a : others){
                        if(!(a instanceof TowerBuilderButton)) continue;
                        TowerBuilderButton tbb = (TowerBuilderButton) a;
                        tbb.goUp();
                    }

                    icon.setY(isChecked ? icon_up : icon_down, Align.center);
                    icon.setDrawable(btn.getSkin().getDrawable(isChecked ? drawable : drawable +"-active"));

                    btn.setChecked(!isChecked);
                }
            });

            addActor(btn);
            addActor(icon);
            setSize(btn.getWidth(), btn.getHeight());
        }

        public void goUp(){
            btn.setChecked(false);
            icon.setDrawable(btn.getSkin().getDrawable(_drawable));
            icon.setPosition(btn.getWidth() / 2, icon_up, Align.center);
        }
    }

    SnapshotArray<Actor> contentChildren;
    public TowerBuilderDialog(Skin skin){
        super("", skin, "siren");
        // padTop(Util.isHD() ? 41 : 32);

        pad(10);

        float spacing = 10;

        // Add Contents and Buttons
        Table content = getContentTable();
        content.pad(10);

        TextButton quadraBtn = new TextButton(Integer.toString((int) Quadra.cost), skin, "towerbuilder");
        TowerBuilderButton quadra = new TowerBuilderButton(quadraBtn, "quadra-icon");
        content.add(quadra).grow().space(spacing);

        TextButton apieraBtn = new TextButton(Integer.toString((int) Apiera.cost), skin, "towerbuilder");
        TowerBuilderButton apiera = new TowerBuilderButton(apieraBtn, "apiera-icon");
        content.add(apiera).grow().space(spacing);

        content.row();

        TextButton hexaBtn = new TextButton(Integer.toString((int) Hexa.cost), skin, "towerbuilder");
        TowerBuilderButton hexa = new TowerBuilderButton(hexaBtn, "hexa-icon");
        content.add(hexa).grow().space(spacing);

        TextButton pentaBtn = new TextButton(Integer.toString((int) Penta.cost), skin, "towerbuilder");
        TowerBuilderButton penta = new TowerBuilderButton(pentaBtn, "penta-icon");
        content.add(penta).grow().space(spacing);

        contentChildren = content.getChildren();

        button("CANCEL");
        button("BUILD");

        setMovable(false);
        pack();
    }

    @Override
    public Dialog show(Stage stage, Action action) {
        return super.show(stage, action);
    }

    @Override
    protected void result(Object object) {
        super.result(object);

        TowerBuilderButton chosen = null;
        for(Actor a : contentChildren){
            if(!(a instanceof TowerBuilderButton)) continue;
            TowerBuilderButton tbb = (TowerBuilderButton) a;
            if(tbb.btn.isChecked()) chosen = tbb;
            tbb.goUp();
        }

        if(chosen == null) return;

        GameMenu menu = (GameMenu) getStage();


    }
}
