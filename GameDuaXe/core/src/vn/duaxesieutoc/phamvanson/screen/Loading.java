package vn.duaxesieutoc.phamvanson.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import vn.duaxesieutoc.phamvanson.MainGame;
import vn.duaxesieutoc.phamvanson.ultis.Assets;
import vn.duaxesieutoc.phamvanson.ultis.LevelAssets;


public class Loading extends ScreenAdapter{
	private MainGame game;
	LevelAssets assets = Assets.getInstance();
	Texture _BGLoad;
	Texture _bar;
	Vector2 pos = new Vector2(63,410);
	float percent = 0;
	boolean flag = true;

	public Loading(MainGame game) {
		super();
		this.game = game;
		assets.addAsset("duaxe.atlas", TextureAtlas.class);
		assets.addAsset("music/bg2.ogg", Music.class);
		assets.addAsset("music/click_button.ogg", Sound.class);
		_BGLoad = new Texture(Gdx.files.internal("dangtai.png"));
		_bar = new Texture(Gdx.files.internal("bar.png"));
	}
	@Override
	public void render(float delta) {
		game.spriteBatch.setProjectionMatrix(game.camera.combined);
		game.spriteBatch.begin();
		game.spriteBatch.draw(_BGLoad,0,0);
		if(assets.getAssetManager().update()) {
			game.setScreen(game.getStartGame());
		}	
		game.spriteBatch.draw(_bar,pos.x,pos.y,_bar.getWidth() * assets.getAssetManager().getProgress() , _bar.getHeight());
		game.spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		//super.resize(width, height);
		game.stage.getViewport().update(width, height, false);
	}
}
