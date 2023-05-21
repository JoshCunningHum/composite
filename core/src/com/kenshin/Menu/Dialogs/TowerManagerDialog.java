package com.kenshin.Menu.Dialogs;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.SnapshotArray;
import com.kenshin.Menu.GameMenu;
import com.kenshin.Tower.Tower;
import com.kenshin.Util;

import java.util.ArrayList;

public class TowerManagerDialog extends Dialog {

    TextButton sell, mod, upg;

    ArrayList<TextButton> btns = new ArrayList<>();
    public TowerManagerDialog(Skin skin){
        super("", skin, "towerbuilder-t1");

//        reset();

        getContentTable().reset();
        getButtonTable().reset();
        getButtonTable().defaults().space(10);

        sell = new TextButton("19", skin, "towerbuilder-red");
        mod = new TextButton("19", skin, "towerbuilder");
        upg = new TextButton("19", skin, "towerbuilder-green");

        button(sell, "sell");
        button(mod, "mod");
        button(upg, "upg");


        btns.add(sell);
        btns.add(mod);
        btns.add(upg);

//        TextButton $sell = new TextButton("", skin, "siren");
//        TextButton $mod = new TextButton("", skin, "siren");
//        TextButton $upg = new TextButton("", skin, "siren");
//
//        button($sell, "sell");
//        button($mod, "mod");
//        button($upg, "upgrade");
        pack();
    }

    public void setPriceTags(Tower t){
        pack();
    }

    @Override
    protected void result(Object object) {
        super.result(object);

        GameMenu menu = (GameMenu) getStage();
        menu._removeTempListeners();

        for(TextButton b : btns) b.setChecked(false);

        String res = (String) object;

        // Base on what is clicked
        switch(res){
            case "sell":

                break;
            case "mod":

                break;
            case "upg":

                break;
        }
    }
}
