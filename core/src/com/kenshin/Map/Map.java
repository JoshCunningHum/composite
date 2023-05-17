package com.kenshin.Map;

import com.kenshin.GameObject.GameObject;
import com.kenshin.Menu.GameMenu;

import java.util.ArrayList;

public class Map extends GameObject {
    MapConfig config;
    AsciiMap _ascii;
    public ArrayList<Block> blocks;

    public Map(MapConfig c){
        super();
        config = c;

        _ascii = MapGen.target_gen(c.cols, c.rows, c.minPath());
        blocks = _ascii.generate_blocks();

        Block topLeft = blocks.get(0), botRight = blocks.get(blocks.size() - 1);

        setBounds(0, 0, botRight.getX() - topLeft.getRight(), topLeft.getTop() - botRight.getY());

        for(Block b : blocks){
            addActor(b);
        }
    }
}
