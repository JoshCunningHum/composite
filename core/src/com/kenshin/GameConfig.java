package com.kenshin;

import com.kenshin.Map.MapConfig;

public class GameConfig {
    public MapConfig map;
    int first_wave_delay, start_money, base_health;

    int screenX, screenY;

    public GameConfig setScreenSize(int width, int height){
        screenX = width;
        screenY = height;
        return this;
    }

    public GameConfig setMap(int rows, int cols){
        if(map == null) map = new MapConfig(rows, cols);
        else map.set(rows, cols);
        return this;
    }

    public GameConfig setFirstWaveDelay(int s){
        first_wave_delay = s;
        return this;
    }

    public GameConfig setStartMoney(int startMoney){
        start_money = startMoney;
        return this;
    }

    public GameConfig setBaseHealth(int baseHealth){
        base_health = baseHealth;
        return this;
    }

    public GameConfig set(GameConfig config){
        map = config.map;
        first_wave_delay = config.first_wave_delay;
        start_money = config.start_money;
        base_health = config.base_health;
        screenX = config.screenX;
        screenY = config.screenY;
        return this;
    }
}
