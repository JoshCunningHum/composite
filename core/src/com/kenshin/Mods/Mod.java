package com.kenshin.Mods;

import com.kenshin.Tower.TowerStat;
import com.kenshin.Util;

import java.util.ArrayList;
import java.util.Locale;

public class Mod {
    Mod mod;

    String prop;
    MODE mode;
    double value;
    String name;
    String shownLabel;
    String id;

    int wave_cycle = 0;

    public static enum MODE {
        FLAT,
        PERC,
        EFFX
    }

    public Mod clone(){
        return new Mod(prop, mode, value, name, wave_cycle);
    }

    public void set(String prop, double value){
        this.prop = prop;
        this.value = value;
    }

    public Mod(String prop, MODE mode, double value, String name) {
        this.prop = prop;
        this.mode = mode;
        this.value = value;
        this.name = name;
    }

    public Mod(String prop, MODE mode, double value, String name, int wc) {
        this.prop = prop;
        this.mode = mode;
        this.value = value;
        this.name = name;
        wave_cycle = wc;
        this.id = Util.genString(8);
    }
    public Mod(String prop, MODE mode, double value) {
        this.prop = prop;
        this.mode = mode;
        this.value = value;
    }

    public void setLabel(String text){
        shownLabel = text;
    }

    public String getLabel(){
        if(!(shownLabel == null || shownLabel.isEmpty())) return shownLabel;
        return String.format("%s +%.2f", prop.toUpperCase(Locale.ROOT), (mode == MODE.PERC ? value * 100 : value));
    }

    public ArrayList<Mod> extract(){
        ArrayList<Mod> a = new ArrayList<>();
        if(this.mod != null) a.addAll(mod.extract());
        else a.add(this);
        return a;
    }

    public int count(){
        return 1 + (this.mod == null ? 0 : this.mod.count());
    }

    public TowerStat apply(TowerStat stat){

        switch (mode){
            case FLAT:
            case PERC:
                float oldVal = stat.get(prop);

                // TODO: Do error checking here

                oldVal = (float) ((mode == MODE.FLAT) ? (float) oldVal + value : (float) oldVal * (1 + value));
                stat.set(prop, oldVal);
                break;
            case EFFX:
                break;
        }

        return (this.mod == null) ? stat : this.mod.apply(stat);
    }

    public void addMod(Mod m){
        if(mod == null) mod = m;
        else mod.addMod(m);
    }

    private void addModAt(int index, Mod m, int current){
        if(mod == null) mod = m;
        else if(current == index - 1){
            Mod prev = mod;
            mod = m;
            m.mod = prev;
        }else mod.addModAt(index, m, current + 1);
    }

    public void addModAt(int index, Mod m){
        addModAt(index, m, 0);
    }

    public void remove(Mod m){
        if(mod.equals(m)){
            mod = mod.mod;
            m.mod = null;
        }else if(mod == null) return;
        else this.mod.remove(m);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Mod)) return false;
        return id.equals(((Mod) obj).id);
    }
}
