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
    Columns columns;
    float time;
    float velocity;
    int moveto;
    
    public CPUPlayer(Columns _cols){
        columns = _cols;
        time = 0;
        velocity = 1;
        moveto = 30;
        //columns.cpuActive = columns.start;
    }
    
    public void update(float delta){
        //columns.cpuActive.update(delta);
        time += delta;
        if(time > velocity){
            //columns.changeActive(columns.active[1].leftC,1, 1);
            time = 0;
            
        }
        
    }
    
    public void checkheight(){
        int mincant = 20;
        int minind = 0;
        /*for(int i = 0; i < columns.active[1].table.length;i++){
            for(int j = columns.active[1].table[0].length - 1;j>=0;j--){
                if((columns.active[1].table[i][j] != 0 || j == 0) && j < mincant){
                    mincant = j;
                    minind = i;
                    break;
                }
            }
        }*/
        moveto = minind;
    }
    
    public void render(SpriteBatch batch){
        
    }
}
