/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielmurillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Daniel
 */
public class NextPiece {
    Vector2 position;
    float width, height;
    float blockwidth, blockheight;
    Piece piece;
    int[][] matrix;
    String type;
    int color;
    Texture back;
    
    public NextPiece(Vector2 _position, float _width, float _height, Piece _piece){
        position = _position;
        width = _width;
        height = _height;
        piece = _piece;
        blockwidth = width/4;
        blockheight = height/4;
        type = "C";
        color = -1;
        back = new Texture(Gdx.files.internal("back-black.bmp"));
    }
    
    public void update(){
        if(type != piece.nextType || color != piece.nextColor){
            getNextPiece();
        }
    }
    
    public void render(SpriteBatch batch, Texture[] textures){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] > 0)
                    batch.draw(textures[matrix[i][j] - 1],position.x+i*blockwidth,position.y+j*blockheight,blockwidth,blockheight);
                else{
                    batch.draw(back,position.x+i*blockwidth,position.y+j*blockheight,blockwidth,blockheight);
                }
            }
        }
        
    }
    
    public void getNextPiece(){
        type = piece.nextType;
        color = piece.nextColor;
        if(type == "O")
        {
            matrix = new int[4][4];
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[2][1] = color;
            matrix[2][2] = color;
        }
        else if(type == "I")
        {
            matrix = new int[4][4];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[1][3] = color;
        }
        else if(type == "S"){
            matrix = new int[4][4];
            matrix[0][0] = color;
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
        }
        else if (type == "Z"){
            matrix = new int[4][4];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[1][0] = color;
            matrix[2][0] = color;
        }
        else if (type == "L"){
            matrix = new int[4][4];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[2][0] = color;
        }
        else if (type == "J"){
            matrix = new int[4][4];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[0][0] = color;
        }
        else if (type == "T"){
            matrix = new int[4][4];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[1][0] = color;
        }
    }
    
}
