/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielmurillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Daniel
 */
public class GameScreen implements Screen{
    final Main game;
    
    private OrthographicCamera camera;
    
    SpriteBatch batch;
    BitmapFont font;
    ShapeRenderer shape;
    
    NextPiece nextp;
    Columns columns;
    private CPUPlayer cpu;
    private Texture[] textures;
    private Score score;
    
    
    
    GameScreen(final Main _game){
        game = _game;
        batch = game.batch;
        font = game.font;
        shape = game.shape;
        
        //Prepare the camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1280,720);
        
        columns = new Columns(0,10,700,1000);
        
        textures = new Texture[3];
        textures[0] = new Texture (Gdx.files.internal("block-red.bmp"));
        textures[1] = new Texture (Gdx.files.internal("block-blue.bmp"));
        textures[2] = new Texture (Gdx.files.internal("block-green.bmp"));
        
        nextp = new NextPiece(new Vector2(1050,700-230), 230, 230,columns.columns[0].piece[0]);
        score = new Score(columns,1050,700-230,230);
        
        cpu = new CPUPlayer(columns);
        
         //Sets the input processor
        MyInputProcessor inputs = new MyInputProcessor(columns, camera);
        Gdx.input.setInputProcessor(inputs);
        
    }

    @Override
    public void show() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float delta) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        //Color the background
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //updates the matrix of the camera
        camera.update();
        
        //Transforms the camera coordinates into screen coordinates
        game.batch.setProjectionMatrix(camera.combined);
        
        //Updates everything
        columns.update(delta);
        cpu.update(delta);
        nextp.update();
        score.update();

        
        //display everything
        batch.begin();
        //shape.begin(ShapeType.Line);
        
        columns.render(delta, batch, textures);
        nextp.render(batch, textures);
        score.render(batch,font);
        //shape.end();
        
        //font.draw(batch,"Hola",1200,720);
        batch.end();
        
    }

    @Override
    public void resize(int width, int height) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
