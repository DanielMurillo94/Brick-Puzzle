/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielmurillo;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Daniel
 */
public class Score {
    
    long score;
    Columns cols;
    Vector2 position;
    float width;
    
    public Score(Columns col, float x, float y, float _width){
        score = 0;
        cols = col;
        position = new Vector2(x,y);
        width = _width;
    }
    
    public void update(){
        score = 0;
        Column c = cols.start;
        do{
            score += c.score;
            c = c.rightC;
        }while(c!= cols.start);
    }
    
    public void render(SpriteBatch batch, BitmapFont font){
        font.draw(batch, "SCORE: "+score, position.x, position.y, width, 0, true);
    }
}
