package com.kenshin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kenshin.Map.Map;
import com.kenshin.Menu.GameMenu;
import com.kenshin.Menu.MainMenu;
import com.kenshin.Menu.Menu;
import com.kenshin.Menu.UpgradeMenu;

public class Game extends ApplicationAdapter {

	static final int MAX_COLS = 10, MAX_ROWS = 12;

	// default configuration
	static GameConfig default_config = new GameConfig()
			.setMap(MAX_COLS, MAX_ROWS)
			.setFirstWaveDelay(7)
			.setStartMoney(150)
			.setBaseHealth(20)
			.setScreenSize(607, 1080);

	// altered config due to upgrades and settings
	public GameConfig config = new GameConfig();

	// Game stats and objects
	private int _health;
	private Map map;

	// Graphical Objects
	public MainMenu mainMenu;
	public GameMenu gameMenu;
	public UpgradeMenu upgradeMenu;

	Menu current_menu;

	public void switchMenu(Menu menu){
		if(current_menu != null) current_menu.exit();
		current_menu = menu;
		Gdx.input.setInputProcessor(menu);
		current_menu.show();
	}

	@Override
	public void create () {
		// apply default config
		config.set(default_config);

		// apply util variables
		Util.default_screen = new ScreenViewport();

		Util.screenX = Gdx.graphics.getWidth();
		Util.screenY = Gdx.graphics.getHeight();

		Util.Theme.init();
		System.out.println("Screen Dimension: " + Util.screenX + " x " + Util.screenY);

		// TODO: apply upgrades and power-ups

		// Initialize menus
		mainMenu = new MainMenu(this);
		gameMenu = new GameMenu(this);
		upgradeMenu = new UpgradeMenu(this);


		// test
		switchMenu(mainMenu);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		float delta = Gdx.graphics.getDeltaTime();

		current_menu.act(delta);
		current_menu.draw();
	}
	
	@Override
	public void dispose () {

	}
}
