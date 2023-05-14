package com.kenshin.Tower;

import java.util.HashMap;

public class TowerStatBuilder { // only used for building the base stat
    public HashMap<String, Double> stat;

    public TowerStatBuilder set(String key, double val){
        stat.put(key, val);
        return this;
    }
}
