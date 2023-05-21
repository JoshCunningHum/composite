package com.kenshin.Tower;

import com.kenshin.Mods.ModEffect;

import java.util.ArrayList;
import java.util.HashMap;

public class TowerStat {
    // Stats are hashmaps
    final private HashMap<String, Float> _final = new HashMap<>();
    final private HashMap<String, Float> _base = new HashMap<>();
    final private ArrayList<ModEffect> _effects = new ArrayList<>();

    TowerStat(TowerStat other){
        other._base.putAll(_base);
        clear();
    }

    TowerStat(TowerStatBuilder builder){
        builder.stat.putAll(_base);
    }

    public float get(String stat){
        return _final.get(stat);
    }

    public TowerStat set(String stat, float value){
        _final.put(stat, value);
        return this;
    }

    public void addEffect(ModEffect effect){
        _effects.add(effect);
    }


    public void clear(){
        _final.clear();
        _base.putAll(_final);
        _effects.clear();
    }
}
