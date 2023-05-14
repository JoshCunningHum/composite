package com.kenshin.Map;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class MapGen {
    static RandomXS128 rand = new RandomXS128();
    static boolean DEBUG_GEN = false;
    static boolean DEBUG_REGENERATING_GEN = true;

    public static AsciiMap gen(int x, int y) {
        PathPoint spawn = new PathPoint(rand.nextInt(x), 0, 0);
        Array<PathPoint> path = new Array<>();

        int size = x * y, half = size / 2, third = size / 3;
        AsciiMap map = new AsciiMap(x, y);

        path.add(spawn);
        path.add(spawn.cpy().add(0, 1));

        map.setAt(spawn, AsciiMap.SPAWN);
        map.setAt(spawn.cpy().add(0, 1), AsciiMap.PATH);

        do {
            PathPoint last = path.get(path.size - 1), p = last.cpy();
            int lx = last.x, ly = last.y;
            ArrayList<Integer> choices = new ArrayList<>();

            if(DEBUG_GEN){
                map.print();
                System.out.printf("Last path coordinate: (%d, %d) (%c) \n", lx, ly, map.getAt(0, 0));
            }

            boolean top = true,
                    left = true,
                    right = true,
                    bottom = true;

            // top
            if (!(map.isGenSafe(lx, ly - 2)
                    && map.isGenSafe(lx, ly - 1)
                    && map.isGenSafe(lx - 1, ly - 1)
                    && map.isGenSafe(lx + 1, ly - 1))) top = false;

            // left
            if (!(map.isGenSafe(lx - 2, ly)
                    && map.isGenSafe(lx - 1, ly)
                    && map.isGenSafe(lx - 1, ly - 1)
                    && map.isGenSafe(lx - 1, ly + 1))) left = false;

            // right
            if (!(map.isGenSafe(lx + 2, ly)
                    && map.isGenSafe(lx + 1, ly)
                    && map.isGenSafe(lx + 1, ly - 1)
                    && map.isGenSafe(lx + 1, ly + 1))) right = false;

            // bottom
            if (!(map.isGenSafe(lx, ly + 2)
                    && map.isGenSafe(lx, ly + 1)
                    && map.isGenSafe(lx - 1, ly + 1)
                    && map.isGenSafe(lx + 1, ly + 1))) bottom = false;

            if (top) choices.add(PathPoint.getDirNum("top"));
            if (bottom) choices.add(PathPoint.getDirNum("bottom"));
            if (left) choices.add(PathPoint.getDirNum("left"));
            if (right) choices.add(PathPoint.getDirNum("right"));

            if (choices.size() == 0) {
                System.out.println("No available path can be generated!");
                break;
            }

            int rolled = (choices.size() == 1) ? choices.get(0) : choices.get(rand.nextInt(choices.size()));
            PathPoint chosenDir = p.addDir(rolled);

            path.add(chosenDir);
            map.setAt(chosenDir, AsciiMap.PATH);

            // debugging
            if(!DEBUG_GEN) continue;
            System.out.print("Available paths: [ ");
            for(int pathNum : choices){
                System.out.print(PathPoint.getDirString(pathNum) + " ");
            }
            System.out.print("] " + choices + " \n");
            System.out.println("Chosen Path: " + PathPoint.getDirString(rolled));

        } while (third >= path.size || rand.nextInt(half - path.size) != 0);

        map.setAt(path.get(path.size - 1), AsciiMap.BASE);

        return map;
    }

    public static AsciiMap target_gen(int x, int y, int minimum){
        AsciiMap map = gen(x, y);
        int count = 0;
        while(map.path_count < minimum){
            map = gen(x, y);
            count++;
        }
        if(DEBUG_REGENERATING_GEN) System.out.println("Map regenerated " + count + " times");
        return map;
    }
}
