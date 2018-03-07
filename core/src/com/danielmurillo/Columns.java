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
 */
public class Columns {
    public Vector2 position;
    public int height, width;
    public Column start;
    public Column[] active;//The column active for the player
    public Column center;
    public String[] colors;
    public int quantity;
    public boolean gameOver;
    
    public Columns(int _pX, int _pY, int _height, int _width, int _quantity){
        setColors();//Starts the array with the colors
        position = new Vector2(_pX, _pY);
        height = _height;
        width = _width;
        quantity = _quantity;
        if(quantity > colors.length)
            quantity = colors.length;
        int colwidth = width /quantity;
        int margin = 20;
        active = new Column[2];
        Column rac = null;
        for(int i = 0; i < quantity; i++){
            Column col = new Column(position.x+margin/2+colwidth*i,position.y,height,colwidth - margin,colors[i]);
            addColumn(col);
            if(i!=0){
                col.piece[1] = null;
            }
            else{
                //col.piece[1] = null;
                active[1] = col;
            }
            if(i != quantity/2){
                col.piece[0] = null;
            }
            else
                rac = col;
        }
        active[0] = rac;
        center = rac;
        gameOver = false;
    }
    
    
    
    public void update(float delta){
        if(!gameOver){
            Column c = start;
            do{//updates all 3 columns
                for(int i = 0; i < c.piece.length; i ++){
                    if(c.update(delta,c.piece[i])){//If the piece was placed then change the active to the center
                        if(i==0){
                            changeActive(center,i,i);
                        }
                    }
                    if(c.gameOver)
                        gameOver = true;
                }
                c = c.rightC;
            }while(c != start);
        }
        else{
            active[0].piece = null;
            active[1].piece = null;
        }
    }
    
    public void render(float delta, SpriteBatch batch, Texture[] textures){
        Column c = start;
        do{
            c.render(delta,batch,textures);
            c = c.rightC;
        }while(c != start);
        if(gameOver){
            batch.draw(textures[0],position.x, position.y, width, height);
        }
    }
    
    public void setColors(){
        colors = new String[3];
        colors[0] = "red";
        colors[1] = "green";
        colors[2] = "blue";
    }
    
    public void addColumn(Column c){
        if(start != null){
            active[0].rightC = c;
            c.leftC = active[0];
            c.rightC = start;
            start.leftC = c;
            active[0] = c;
        }
        else{
            start = c;
            active[0] = c;
            c.leftC = c;
            c.rightC = c;
        }
    }
    
    public void changeActive(Column c, int indexC,int indexF){
        if(c != active[indexC]){//if is not trying to change to the same column
            if(c.maxHeight < active[indexC].piece[indexF].position.y + active[indexC].piece[indexF].getStartOfFigure()){//if the position of the piece is higher than the maximum height of the blocks
                c.piece[indexF] = active[indexC].piece[indexF];
                active[indexC].piece[indexF] = null;
                active[indexC] = c;
            }
        }
    }
    
    public void removePenalty(Vector3 vect){
        Column c = start;
        do{
            c.removePenalty(vect);
            c = c.rightC;
        }while(c != start);
    }
}
