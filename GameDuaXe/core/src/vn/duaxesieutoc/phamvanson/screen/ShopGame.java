package vn.duaxesieutoc.phamvanson.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import vn.duaxesieutoc.phamvanson.model.CarShop;
import vn.duaxesieutoc.phamvanson.ultis.Assets;
import vn.duaxesieutoc.phamvanson.ultis.Config;
import vn.duaxesieutoc.phamvanson.ultis.Musics;

/**
 * Created by MIYUTO on 13/11/2017.
 */

public class ShopGame extends ScreenAdapter {
    TextureRegion background,titleShop,coinComponent,coinLabel,vMaxLabel,nitroLabel,boardComponent;
    private List<CarShop> listCarShop;
    private MainGame game;
    TextureAtlas atlas;
    Skin skin;
    Button btnNextCar,btnBackCar,btnBackShop,btnChoose;
    BitmapFont bitmapFont;
    TextureRegion nitro0,nitro1,nitro2;
    private int quantity;
    public ShopGame(MainGame game){
        this.game = game;
        listCarShop = new ArrayList<CarShop>();
        atlas = Assets.getInstance().getAssetManager().get("duaxe.atlas", TextureAtlas.class);
        skin = new Skin(Gdx.files.internal("skinButton.json"),atlas);
        background = atlas.findRegion("BG");
        titleShop = atlas.findRegion("cua hang");
        quantity = 3;
        bitmapFont = new BitmapFont(Gdx.files.internal("font/fontt.fnt"));
        coinComponent = atlas.findRegion("khung-tien");
        coinLabel = atlas.findRegion("gia");
        vMaxLabel = atlas.findRegion("vmax");
        nitroLabel = atlas.findRegion("nitro");
        boardComponent = atlas.findRegion("bang-thong-tin");
        nitro0 = atlas.findRegion("nitro0");
        nitro1 = atlas.findRegion("nitro1");
        nitro2 = atlas.findRegion("nitro2");
        createButton();
        addButton();
        createCarShop();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(game.stage);
    }
    public void createButton(){
        btnBackCar = new Button(skin,"btBackChonMan");
        btnNextCar = new Button(skin,"btNextChonMan");
        btnBackShop = new Button(skin,"btTroVe");
        btnChoose = new Button(skin,"btChon");
        btnBackCar.setBounds(30, 527, 40, 60);
        btnNextCar.setBounds(410, 527, 40, 60);
        btnChoose.setBounds(105, 30, 268, 54);
        btnBackShop.setBounds(368, 0, 119, 27);
    }
    public void addButton(){
        game.stage.addActor(btnBackCar);
        game.stage.addActor(btnBackShop);
        game.stage.addActor(btnChoose);
        game.stage.addActor(btnNextCar);
        btnBackShop.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                deleteActor();
                game.setScreen(game.getChooseLevel());
            }
        });
        btnBackCar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                Config.indexCarChoose -=1;
            }
        });
        btnNextCar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                Config.indexCarChoose += 1;
            }
        });
        btnChoose.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Config.soundEnable) Musics.soundClickButton.play();
                deleteActor();
            }
        });
    }
    private void checkButton(){
        if(Config.indexCarChoose == 0 ){
            btnBackCar.remove();
        }
        else if(Config.indexCarChoose == 2){
            btnNextCar.remove();
        }
        else{
            game.stage.addActor(btnBackCar);
            game.stage.addActor(btnNextCar);
        }
    }
    public void createCarShop(){
        for(int i = 0 ; i < quantity ; i++){
            TextureRegion carUnLock = atlas.findRegion("shopxe" + String.valueOf(i));
            TextureRegion carLock = atlas.findRegion("lock_police" + String.valueOf(i+1));
            CarShop  carShop = new CarShop(carUnLock,carLock);
            carShop.setPositionLock();
            carShop.setPositionUnLock();
            listCarShop.add(carShop);
        }
    }
    public void addNitroAndVMax(int pos,SpriteBatch batch){
        if (Config.indexCarChoose == 0) {
            batch.draw(nitro0, 160, 225);
            batch.draw(nitro0, 160, 175);
        }
        if (Config.indexCarChoose == 1) {
            batch.draw(nitro0, 160, 225);
            batch.draw(nitro1, 160, 175);
        }
        if (Config.indexCarChoose == 2) {
            batch.draw(nitro1, 160, 225);
            batch.draw(nitro2, 160, 175);
        }
    }
    public void addCarUnLock(int pos, SpriteBatch batch){
        batch.draw(listCarShop.get(pos).getCarUnLock(),
                listCarShop.get(pos).getX1(),listCarShop.get(pos).getY1(),
                listCarShop.get(pos).getWidth1(),listCarShop.get(pos).getHeight1());
    }
    public void addCarLock(int level,SpriteBatch batch){
        if(level < 3 && Config.indexCarChoose == 1){
            batch.draw(listCarShop.get(1).getCarLock(),
                    listCarShop.get(1).getX2(),listCarShop.get(1).getY2(),
                    listCarShop.get(1).getWidth2(),listCarShop.get(1).getHeight2());
        }
        if(level < 5 && Config.indexCarChoose == 2){
            batch.draw(listCarShop.get(2).getCarLock(),
                    listCarShop.get(2).getX2(),listCarShop.get(2).getY2(),
                    listCarShop.get(2).getWidth2(),listCarShop.get(2).getHeight2());
        }
    }
    public void deleteActor(){
        btnBackShop.remove();
        btnChoose.remove();
        btnNextCar.remove();
        btnBackCar.remove();
    }
    @Override
    public void render(float delta) {
        checkButton();
        game.spriteBatch.setProjectionMatrix(game.camera.combined);
        game.spriteBatch.begin();
        game.spriteBatch.draw(background,0,0);
        game.spriteBatch.draw(titleShop,40, 700, 404, 50);
        game.spriteBatch.draw(coinComponent,220, 360, 226, 33);
        game.spriteBatch.draw(boardComponent,40, 105, 400, 233);
        game.spriteBatch.draw(coinLabel,60, 275, 130, 33);
        game.spriteBatch.draw(vMaxLabel,60, 225, 130, 33);
        game.spriteBatch.draw(nitroLabel,60, 175, 130, 33);
        addCarUnLock(Config.indexCarChoose,game.spriteBatch);
        addCarLock(Config.levelGame,game.spriteBatch);
        addNitroAndVMax(Config.indexCarChoose,game.spriteBatch);
        game.spriteBatch.end();
        game.stage.draw();
    }
}
