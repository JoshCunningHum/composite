package com.kenshin.Tower;

import java.util.HashMap;

public class TowerStatBuilder { // only used for building the base stat
    public HashMap<String, Float> stat;

    public TowerStatBuilder(){
        // TODO: Apply Upgrades
        set("atk", 0);
        set("spd", 0);
        set("rng", 0);
        set("trg", 0);
        set("pen", 0);
    }

    public TowerStatBuilder set(String key, float val){
        stat.put(key, val);
        return this;
    }

}
