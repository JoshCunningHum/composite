package com.kenshin.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kenshin.Menu.GameMenu;

public class Site extends Block{
    public static Texture texture = new Texture(Gdx.files.internal("blocks/site.png"));
    public static Texture selected = new Texture(Gdx.files.internal("blocks/site-selected.png"));

    public Site() {
        super(texture);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);


                final GameMenu menu = (GameMenu) getStage();
                // TODO: Create action where the block is referenced to create a tower where the block is located
                menu.showDialogs(menu.dTowerBuilder, new RunnableAction(){
                    @Override
                    public void run() {
                        menu.setSelectedSite(Site.this);
                    }
                });
            }
        });
    }
}
