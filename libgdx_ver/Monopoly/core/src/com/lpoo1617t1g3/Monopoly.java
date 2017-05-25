package com.lpoo1617t1g3;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import states.GameStateManager;
import states.MenuState;

public class Monopoly extends ApplicationAdapter {
	public final static int WIDTH  = 800;
	public final static int HEIGHT = 450;
	public static final String TITLE  = "Monopoly";
	private GameStateManager gsm;
	private SpriteBatch spb;
	
	@Override
	public void create () {
		spb = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(spb);
	}
	
	@Override
	public void dispose () {
		spb.dispose();
	}
}
