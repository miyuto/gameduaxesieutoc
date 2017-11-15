package vn.duaxesieutoc.phamvanson.ultis;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by MIYUTO on 11/11/2017.
 */

public class Musics {
    public static  Music musicBG;
    public static Sound soundClickButton;
    public static  void loadFile(){
        musicBG = Assets.getInstance().getAssetManager().get("music/bg2.ogg");
        soundClickButton = Assets.getInstance().getAssetManager().get("music/click_button.ogg");
    }
}
