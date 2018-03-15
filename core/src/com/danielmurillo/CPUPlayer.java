/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielmurillo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Daniel   
 */
public class CPUPlayer {
    //Columns columns;
    Column column;
    Piece piece;
    float time;
    float velocity;
    int moveto;
    
    public CPUPlayer(Column _cols){
        column = _cols;
        piece = column.piece[0];
        time = 0;
        velocity = 1;
        moveto = 30;
        //columns.cpuActive = columns.start;
    }
    
    public void update(float delta){
        //columns.cpuActive.update(delta);
        time += delta;
        if(time > velocity){
            time = 0;
            if(piece.reseted){
                checkheight();
                piece.reseted = false;
            }
            if(piece.position.x + piece.fStart < moveto)
                column.moveRight(0);
            else if(piece.position.x + piece.fStart > moveto)
                column.moveLeft(0);
            else
                rotate();
            
        }
        
    }
    
    public void checkheight(){
        int mincant = 20;
        int minind = 0;
        for(int i = 0; i < column.table.length;i++){
            for(int j = column.table[0].length - 1;j>=0;j--){
                if((column.table[i][j] != 0 || j == 0) && j < mincant){
                    mincant = j;
                    minind = i;
                    break;
                }
            }
        }
        moveto = minind;
    }
    
    public void rotate(){
        
    }
    
    public void render(SpriteBatch batch){
        
    }
}
