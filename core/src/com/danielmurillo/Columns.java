    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielmurillo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Daniel
 * The part of the GameScreen that contains both the play space of the player and the CPU
 */
public class Columns {
    public Vector2 position;
    public int height, width;
    public Column[] columns;
    public String[] colors;
    public boolean gameOver;
    
    public Columns(int _pX, int _pY, int _height, int _width){
        setColors();//Starts the array with the colors
        position = new Vector2(_pX, _pY);
        height = _height;
        width = _width;
        int colwidth = width /2;//Is divided by 2 because there are 2 columns
        int margin = 20;
        columns = new Column[2];
        for(int i = 0; i < 2; i++){//Initialize every column
            columns[i] = new Column(position.x+margin/2+colwidth*i,position.y,height,colwidth - margin,colors[i]);
        }
        gameOver = false;
    }
    
    
    
    public void update(float delta){
        if(!gameOver){//Updates the 2 columns
            for(int i = 0; i < 2 ; i ++){
                columns[i].update(delta);
            }
        }
        else{
            columns[0].piece = null;
            columns[1].piece = null;
        }
    }
    
    public void render(float delta, SpriteBatch batch, Texture[] textures){
        if(gameOver){
            batch.draw(textures[0],position.x, position.y, width, height);
        }
        else{
            for(int i = 0; i < 2 ; i ++){
                columns[i].render(delta,batch,textures);
            }
        }
    }
    
    public void setColors(){
        colors = new String[2];
        colors[0] = "red";
        colors[1] = "blue";
    }
}
