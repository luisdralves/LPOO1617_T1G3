package com.lpoo1617t1g3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import screens.MainMenu;

public class Monopoly extends Game {
	public final static int WIDTH  = 1280;
	public final static int HEIGHT = 720;
	public final static String TITLE  = "Monopoly";
	public static BitmapFont kabelBlack;
	public static TextureAtlas atlas;
	public static Skin skin;
	public static Label.LabelStyle lblStyle;
	public static TextButton.TextButtonStyle btnStyle;
	public static TextField.TextFieldStyle tflStyle;
	public static ShapeRenderer shapeRenderer;
	public Music musicIntro;
	public Music musicLoop;
	public SpriteBatch spb;
	private Screen screen;
	private boolean gameOver;

	@Override
	public void create () {
		spb = new SpriteBatch();
		Gdx.gl.glClearColor(1, 1, 1, 1);

		atlas = new TextureAtlas("tpack/tpack.atlas");
		skin = new Skin(atlas);
		kabelBlack = new BitmapFont(Gdx.files.internal("Kabel.fnt"));
		lblStyle = new Label.LabelStyle(kabelBlack, Color.BLACK);
		btnStyle = new TextButton.TextButtonStyle();
		btnStyle.up = skin.getDrawable("btn_up");
		btnStyle.down = skin.getDrawable("btn_down");
		btnStyle.over = skin.getDrawable("btn_hover");
		btnStyle.disabled = skin.getDrawable("btn_dis");
		btnStyle.font = kabelBlack;
		tflStyle = new TextField.TextFieldStyle();
		tflStyle.cursor = skin.getDrawable("tfl_cur");
		tflStyle.selection = skin.getDrawable("tfl_sel");
		tflStyle.background = skin.getDrawable("tfl_bg");
		tflStyle.font = kabelBlack;
		tflStyle.fontColor = Color.BLACK;
		shapeRenderer = new ShapeRenderer();

		screen = new MainMenu(this);
		setScreen(screen);
		gameOver = false;
		musicInit();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		spb.dispose();
	}

	private void musicInit() {
		musicIntro = Gdx.audio.newMusic(Gdx.files.internal("money_intro.ogg"));
		musicLoop = Gdx.audio.newMusic(Gdx.files.internal("money_loop.ogg"));
		musicLoop.setLooping(true);
		musicIntro.setOnCompletionListener(new Music.OnCompletionListener(){
			@Override
			public void onCompletion(Music aMusic){
				musicLoop.play();
			}
		});
		musicIntro.play();
	}
}
