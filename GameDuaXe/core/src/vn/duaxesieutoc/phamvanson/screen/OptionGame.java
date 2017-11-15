package vn.duaxesieutoc.phamvanson.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import vn.duaxesieutoc.phamvanson.MainGame;
import vn.duaxesieutoc.phamvanson.ultis.Assets;
import vn.duaxesieutoc.phamvanson.ultis.Config;
import vn.duaxesieutoc.phamvanson.ultis.Musics;

/**
 * Created by MIYUTO on 11/11/2017.
 */

public class OptionGame extends ScreenAdapter{
    private MainGame game;
    TextureAtlas atlas;
    TextureRegion background;
    TextureRegion optionBG;
    TextureRegion soundImage;
    TextureRegion musicImage;
    Button btnSound,btnMusic,btnBack;
    Skin skin;

    public OptionGame(MainGame game) {
        this.game = game;
        atlas = Assets.getInstance().getAssetManager().get("duaxe.atlas",TextureAtlas.class);
        background = atlas.findRegion("hinhnen");
        optionBG = atlas.findRegion("option-BG");
        soundImage = atlas.findRegion("am-thanh");
        musicImage = atlas.findRegion("nhac-nen");
        skin = new Skin(Gdx.files.internal("skinButton.json"), atlas);
        btnSound = new Button(skin,"btSound");
        btnMusic = new Button(skin,"btMusic");
        btnBack = new Button(skin,"btTroVe");

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(game.stage);
        btnSound.setBounds(480 / 2 - 395 / 2 + 218, 2 * 800 / 3 - 200,
                122, 33);
        btnMusic.setBounds(480 / 2 - 395 / 2 + 218, 2 * 800 / 3 - 380,
                122, 33);
        btnBack.setBounds(480 - 122, 0, 122, 43);
        game.stage.addActor(btnSound);
        game.stage.addActor(btnMusic);
        game.stage.addActor(btnBack);
        if(Config.musicEnable) btnMusic.setChecked(true);
        else btnMusic.setChecked(false);
        if(Config.soundEnable) btnSound.setChecked(true);
        else btnSound.setChecked(false);
        btnBack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                deleteActor();
                game.setScreen(game.getStartGame());
            }
        });
        btnMusic.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                if(Config.musicEnable){
                    btnMusic.setBackground(skin.getDrawable("tat"));
                    Config.musicEnable = false;
                    Musics.musicBG.pause();
                }
                else{
                    btnMusic.setBackground(skin.getDrawable("bat"));
                    Config.musicEnable = true;
                    Musics.musicBG.setLooping(true);
                    Musics.musicBG.play();
                }
            }
        });
        btnSound.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                if(Config.soundEnable){
                    btnSound.setBackground(skin.getDrawable("tat"));
                    Config.soundEnable = false;
                }
                else{
                    btnSound.setBackground(skin.getDrawable("bat"));
                    Config.soundEnable = true;
                }
            }
        });


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render(delta);
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(background,0,0);
        game.spriteBatch.draw(optionBG,480 / 2 - 410 / 2,
                (2 * 800 / 3) - 450, 410, 486);
        game.spriteBatch.draw(soundImage, 480 / 2 - 395 / 2, (2 * 800 / 3) - 150,
                395, 50);
        game.spriteBatch.draw(musicImage, 480 / 2 - 395 / 2, (2 * 800 / 3) - 330,
                395, 50);
        game.spriteBatch.end();
        game.stage.draw();


    }
    void deleteActor(){
        btnBack.remove();
        btnSound.remove();
        btnMusic.remove();
    }

    @Override
    public void dispose() {
    }
}
