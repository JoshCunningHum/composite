package com.kenshin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Util {
    static public int screenX = 0, screenY = 0;
    static public Viewport default_screen;

    static public boolean isInBound(int min, int max, int value, int mode) {
        try {

            switch (mode){
                case 0:
                    return min <= value && max >= value;
                case 1:
                    return min < value && max > value;
                case 2:
                    return min <= value && max > value;
                case 3:
                    return min < value && max >= value;
            }
            throw new Exception("Mode: " + mode + " is not a documented mode (0, 1, 2, 3)");
        }catch (Exception e){
            System.err.print(e.getMessage());
        }
        return false;
    }

    static public boolean isInBound(int min, int max, int value) {
        return isInBound(min, max, value, 0);
    }

    static public double getScreenDiagonal(){
        return Math.sqrt(screenX * screenX + screenY * screenY);
    }

    static public boolean isHD(){
        return getScreenDiagonal() > 1000f;
    }

    static public class Theme {
        private static boolean _isHD = false;

        public static void init(){
            Siren = new Skin(Gdx.files.internal("skins/siren/siren.json"));
            SirenHD = new Skin(Gdx.files.internal("skins/sirenhd/siren-hd.json"));

            _isHD = isHD();
        }
        public static Skin Siren, SirenHD;

        public static Skin Siren(){
            return _isHD ? SirenHD : Siren;
        }
    }

    static public class UI {
        static public class Textures {
            static public Texture ico_heart = new Texture(Gdx.files.internal("ui/heart.png")),
                                  ico_gold = new Texture(Gdx.files.internal("ui/gold.png"));
        }
    }

}
