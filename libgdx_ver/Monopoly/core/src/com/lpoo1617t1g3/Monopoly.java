package com.lpoo1617t1g3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import logic.Board;
import logic.Player;
import logic.GameData;
import screens.MainMenu;
import screens.PlayScreen;

public class Monopoly extends Game {
	public final static int WIDTH  = 1280;
	public final static int HEIGHT = 720;
	public final static String TITLE  = "Monopoly";
	public static BitmapFont kabelBlack;
	private Screen screen;
	public Music musicIntro;
	public Music musicLoop;
	public SpriteBatch spb;
	private boolean gameOver;

	@Override
	public void create () {
		spb = new SpriteBatch();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		screen = new MainMenu(this);
		setScreen(screen);
		kabelBlack = new BitmapFont(Gdx.files.internal("Kabel.fnt"));
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
