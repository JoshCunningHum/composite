package com.kenshin.Tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kenshin.GameObject.GameObject;
import com.kenshin.Menu.Dialogs.TowerManagerDialog;
import com.kenshin.Menu.GameMenu;

public class Tower extends GameObject {
    public static float uf = 0.3f;
    public static int base_cap = 1;

    public Tower(Texture t){
        super(t);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                final GameMenu menu = (GameMenu) getStage();

                menu.showDialogs(menu.dTowerManager, new RunnableAction(){
                    @Override
                    public void run() {
                        ((TowerManagerDialog) menu.dTowerManager).setPriceTags(Tower.this);
                    }
                });
            }
        });
    }
}
