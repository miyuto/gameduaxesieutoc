package vn.duaxesieutoc.phamvanson.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
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


public class StartGame extends ScreenAdapter{
	private MainGame game;
	TextureAtlas atlas;
	TextureRegion _BG;
	Button btnPlay;
	Button btnOption;
	Button btnExit;
	Button btnSendScore;
	public StartGame(MainGame game) {
		super();
		this.game = game;
		Musics.loadFile();
		atlas = Assets.getInstance().getAssetManager().get("duaxe.atlas", TextureAtlas.class);
		Skin skin = new Skin(Gdx.files.internal("skinButton.json"), atlas);
		_BG = atlas.findRegion("BG2");
		btnPlay = new Button(skin, "btPlay");
		btnOption = new Button(skin,"btOption");
		btnExit = new Button(skin,"btExit");
		btnSendScore = new Button(skin,"btGuiDiem");
		if(Config.musicEnable){
	//		Musics.musicBG.setLooping(true);
	//		Musics.musicBG.play();
			Musics.musicBG.setVolume(0.5f);
		}
		else Musics.musicBG.stop();
	}
	@Override
	public void show() {
		Gdx.input.setInputProcessor(game.stage);
		btnPlay.setBounds(42, 450, 395, 50);
		btnSendScore.setBounds(42,350,395,50);
		btnOption.setBounds(42, 250, 395, 50);
		btnExit.setBounds(480/2-97/2, 162, 97, 38);
		game.stage.addActor(btnPlay);
		game.stage.addActor(btnSendScore);
		game.stage.addActor(btnOption);
		game.stage.addActor(btnExit);

		btnOption.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(Config.soundEnable) Musics.soundClickButton.play();
				deleteAction();
				game.setScreen(game.getOptionGame());
			}
		});
		btnSendScore.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// gửi điểm đến server
			}
		});
		btnPlay.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(Config.soundEnable) Musics.soundClickButton.play();
				deleteAction();
				game.setScreen(game.getChooseLevel());
			}
		});
		btnExit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.getAndroidInterfaces().showDialog("Exit game","Do you want exit game?");
			}
		});

	}
	@Override
	public void render(float delta) {
		game.spriteBatch.setProjectionMatrix(game.camera.combined);
		game.spriteBatch.begin();
		game.spriteBatch.draw(_BG,0,0);
		game.spriteBatch.end();

		game.stage.act();
		game.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		game.stage.getViewport().update(width, height, false);
	}

	@Override
	public void resume() {
		super.resume();
		if(Config.musicEnable) Musics.musicBG.play();

	}

	@Override
	public void dispose() {
		game.stage.dispose();
	}
	void deleteAction(){
		btnOption.remove();
		btnPlay.remove();
		btnExit.remove();
		btnSendScore.remove();
	}

}
