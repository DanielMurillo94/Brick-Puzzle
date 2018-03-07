package com.danielmurillo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends Game {
	SpriteBatch batch;
        BitmapFont font;
        ShapeRenderer shape;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
                font = new BitmapFont();
                shape = new ShapeRenderer();
                this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
                font.dispose();
                shape.dispose();
	}
}
