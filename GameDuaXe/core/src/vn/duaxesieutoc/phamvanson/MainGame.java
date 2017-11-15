package vn.duaxesieutoc.phamvanson;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import vn.duaxesieutoc.phamvanson.screen.ChooseLevel;
import vn.duaxesieutoc.phamvanson.screen.Loading;
import vn.duaxesieutoc.phamvanson.screen.OptionGame;
import vn.duaxesieutoc.phamvanson.screen.ShopGame;
import vn.duaxesieutoc.phamvanson.screen.StartGame;

public class MainGame extends Game {
	public SpriteBatch spriteBatch;
	public OrthographicCamera camera;
	public Stage stage;
	private AndroidInterfaces androidInterfaces;
	public MainGame(){

	}
	public MainGame(AndroidInterfaces androidInterfaces){
		this.androidInterfaces = androidInterfaces;
	}

	public AndroidInterfaces getAndroidInterfaces() {
		return androidInterfaces;
	}

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 480, 800);
		camera.position.set(new Vector2( 240,  400), 0);
		stage = new Stage(new StretchViewport(480,800),spriteBatch);
		setScreen(getScreenLoading());

	}
	@Override
	public void dispose() {
		super.dispose();
	}
	@Override
	public void render() {
		super.render();
	}
	public Loading getScreenLoading() {
		return new Loading(this);
	}
	public StartGame getStartGame() {
		return new StartGame(this);
	}
	public OptionGame getOptionGame(){
		return new OptionGame(this);
	}
	public ChooseLevel getChooseLevel(){return new ChooseLevel(this);}
	public ShopGame getShopGame(){return  new ShopGame(this);}

}
