package vn.duaxesieutoc.phamvanson.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


import java.util.ArrayList;
import java.util.List;

import vn.duaxesieutoc.phamvanson.MainGame;
import vn.duaxesieutoc.phamvanson.model.MapShop;
import vn.duaxesieutoc.phamvanson.ultis.Assets;
import vn.duaxesieutoc.phamvanson.ultis.Config;
import vn.duaxesieutoc.phamvanson.ultis.Musics;

/**
 * Created by MIYUTO on 12/11/2017.
 */

public class ChooseLevel extends ScreenAdapter{
    private MainGame game;
    TextureRegion background,backgroundFrame,imageChooseLevel;
    TextureAtlas atlas;
    private List<MapShop> listMapShop;
    private int quantityMap;
    Button btnBackMain,btnNextShop,btnBackShop;
    Skin skin;
    Button btnChoose;
    public ChooseLevel(MainGame game){
        this.game = game;
        atlas = Assets.getInstance().getAssetManager().get("duaxe.atlas", TextureAtlas.class);
        skin = new Skin(Gdx.files.internal("skinButton.json"),atlas);
        background = atlas.findRegion("BG");
        backgroundFrame = atlas.findRegion("khung");
        imageChooseLevel = atlas.findRegion("chon-man");
        listMapShop = new ArrayList<MapShop>();
        quantityMap = 6;
        createMapShop();
        createButton();
        addButton();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(game.stage);
    }

    private void createMapShop(){
        for(int i = 0 ; i < quantityMap ; i++){
            TextureRegion mapImage = atlas.findRegion("mapchon"+String.valueOf(i));
            TextureRegion mapLock = atlas.findRegion("lock-screen");
            listMapShop.add(new MapShop(mapImage,mapLock));
        }
    }
    private void addMapUnlock(int pos, float delta, SpriteBatch batch){
        batch.draw(listMapShop.get(pos).getMapUnLock(),
                listMapShop.get(pos).getPos().x, listMapShop.get(pos).getPos().y,
                listMapShop.get(pos).getWidth(), listMapShop.get(pos).getHeight());
    }
    private  void addMapLock(int pos,SpriteBatch batch){
        if(pos >= Config.levelGame){
            batch.draw(listMapShop.get(pos).getMapLock(),
                    listMapShop.get(pos).getPos().x, listMapShop.get(pos).getPos().y,
                    listMapShop.get(pos).getWidth(), listMapShop.get(pos).getHeight());
        }
    }
    private void createButton(){
        btnBackMain = new Button(skin,"btTroVe");
        btnNextShop = new Button(skin,"btNextChonMan");
        btnBackShop = new Button(skin,"btBackChonMan");
        btnChoose = new Button(skin,"btChon");
        btnBackMain.setBounds(368, 0, 119, 27);
        btnNextShop.setBounds(392, 362, 40, 60);
        btnBackShop.setBounds(50, 362, 40, 60);
        btnChoose.setBounds(108, 55, 268, 51);
    }
    private void checkButton(){
        if(Config.indexMapChoose == 0 ){
            btnBackShop.remove();
        }
        else if(Config.indexMapChoose == 5){
            btnNextShop.remove();
        }
        else{
            game.stage.addActor(btnBackShop);
            game.stage.addActor(btnNextShop);
        }
    }
    private  void addButton(){
        game.stage.addActor(btnBackMain);
        btnBackMain.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                deleteActor();
                game.setScreen(game.getStartGame());
            }
        });
        btnNextShop.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                Config.indexMapChoose += 1;
            }
        });
        btnBackShop.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                Config.indexMapChoose -= 1;
            }
        });
        btnChoose.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                if(Config.indexMapChoose >= Config.levelGame){
                    game.getAndroidInterfaces().toast("Map lock");
                }
                else{
                    // chuyển sang màn hình chọn xe
                    deleteActor();
                    game.setScreen(game.getShopGame());
                }
            }
        });
        game.stage.addActor(btnNextShop);
        game.stage.addActor(btnBackShop);
        game.stage.addActor(btnChoose);
    }
    private void deleteActor(){
        btnBackMain.remove();
        btnNextShop.remove();
        btnBackMain.remove();
        btnChoose.remove();
    }
    @Override
    public void render(float delta) {
        checkButton();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(background,0,0);
        game.spriteBatch.draw(imageChooseLevel,40, 720, 402, 50);
        game.spriteBatch.draw(backgroundFrame,20, 100, 444, 603);
        addMapUnlock(Config.indexMapChoose,delta,game.spriteBatch);
        addMapLock(Config.indexMapChoose,game.spriteBatch);
        game.spriteBatch.end();

        game.stage.draw();
    }

    @Override
    public void dispose() {

    }
}
