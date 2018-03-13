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
import java.util.Random;

/**
 *
 * @author Daniel
 */
public class Piece {
    Random ran;
    float unitX, unitY;
    
    Vector2 position;//The position in the grid of the column
    Figure start, active;
    int color;
    int resetX, resetY;
    float velocity;
    float time;
    
    public String nextType,figureType;
    //public int nextColor;
    
    Texture[] textures;
    Texture blocktexture;
    
    //creates the piece that you want
    public Piece(Column col, float _unitX, float _unitY){
        //start = new Figure();
        color = col.color;
        ran = new Random();
        resetX = col.table.length /2;
        resetY = col.table[0].length;
        position = new Vector2(resetX ,resetY);
        unitX = _unitX;
        unitY = _unitY;
        velocity = 0.5f;
        time = 0;
        textures = new Texture[2];
        textures[0] = new Texture (Gdx.files.internal("block-red.bmp"));
        textures[1] = new Texture (Gdx.files.internal("block-blue.bmp"));
        //nextColor = ran.nextInt(2)+1;
        int n = ran.nextInt(7);
        if(n == 0){
            nextType = "O";
        }
        else if(n == 1){
            nextType = "I";
        }
        else if(n == 2){
            nextType = "S";
        }
        else if(n == 3){
            nextType = "Z";
        }
        else if(n == 4){
            nextType = "J";
        }
        else if(n == 5){
            nextType = "L";
        }
        else if(n == 6){
            nextType = "T";
        }
        resetPiece();
        
        //color = ran.nextInt(3)+1;
        //randomFigure();
    }
    
    public boolean update(float delta){
        time += delta;
        if(time > velocity){
            time = 0;
            return true;
        }
        return false;
    }
    
    public void render(float delta, SpriteBatch batch,Column col){
        //setColor(shape);
        for(int i = 0; i < active.figure.length;i++){
            for(int j = 0; j < active.figure[0].length;j++){
                if(active.figure[i][j] > 0)
                //shape.box(col.position.x +position.x*unitX+i*unitX, col.position.y +position.y*unitY + j*unitY, 0, unitX, unitY, 0);
                    batch.draw(blocktexture, col.position.x +position.x*unitX+i*unitX, col.position.y +position.y*unitY + j*unitY, unitX, unitY);
            }
        }
    }
    
    public void resetPiece(){
        //color = nextColor;
        //nextColor = ran.nextInt(2)+1;
        randomFigure();
        position.x = resetX - active.figure.length/2;
        position.y = resetY;
        blocktexture = textures[color-1];
    }
    
    private void randomFigure(){
        figureType = nextType;
        int t = ran.nextInt(7);
        switch(t){
            case 0:
                //createFigure("O");
                nextType = "O";
                break;
            case 1:
                //createFigure("I");
                nextType = "I";
                break;
            case 2:
                //createFigure("S");
                nextType = "S";
                break;
            case 3:
                //createFigure("Z");
                nextType = "Z";
                break;
            case 4:
                //createFigure("J");
                nextType = "J";
                break;
            case 5:
                //createFigure("L");
                nextType = "L";
                break;
            case 6:
                //createFigure("T");
                nextType = "T";
                break;
        }
        createFigure(figureType);
    }
    
    private void createFigure(String type){
        start = null;
        active = null;
        int[][] matrix;
        if(type == "O")
        {
            matrix = new int[3][3];
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[2][1] = color;
            matrix[2][2] = color;
            addFigure(matrix);
        }
        else if(type == "I")
        {
            matrix = new int[2][4];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[1][3] = color;
            addFigure(matrix);
            matrix = new int[4][3];
            matrix[0][2] = color;
            matrix[1][2] = color;
            matrix[2][2] = color;
            matrix[3][2] = color;
            addFigure(matrix);
        }
        else if(type == "S"){
            matrix = new int[3][3];
            matrix[0][0] = color;
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            addFigure(matrix);
            matrix = new int[3][3];
            matrix[1][2] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[2][0] = color;
            addFigure(matrix);
        }
        else if (type == "Z"){
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[1][0] = color;
            matrix[2][0] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[2][2] = color;
            addFigure(matrix);
        }
        else if (type == "L"){
            matrix = new int[3][3];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[2][0] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[2][2] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[0][2] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[0][0] = color;
            addFigure(matrix);
        }
        else if (type == "J"){
            matrix = new int[3][3];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[0][0] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;;
            matrix[2][1] = color;
            matrix[2][0] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[1][0] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[2][2] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[0][2] = color;
            addFigure(matrix);
        }
        else if (type == "T"){
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[1][0] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[1][2] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[1][0] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[2][1] = color;
            matrix[1][2] = color;
            addFigure(matrix);
            
            matrix = new int[3][3];
            matrix[0][1] = color;
            matrix[1][1] = color;
            matrix[1][2] = color;
            matrix[1][0] = color;
            addFigure(matrix);
        }
        
        active = start;
    }
    
    //Add a new "frame" of the Figure to the list
    public void addFigure(int[][] mat){
        Figure n = new Figure(mat);
        if(start != null){
            active.right = n;
            n.left = active;
            n.right = start;
            start.left = n;
            active = n;
        }
        else{
            start = n;
            active = n;
            n.left = n;
            n.right = n;
        }
    }
    
    public int getStartOfFigure(){
        int n = 0;
        boolean ret = false;
        for(int j = 0; j < active.figure[0].length;j++){
            for(int i = 0; i < active.figure.length;i++){
                n = j;
                if(active.figure[i][j] != 0){
                    ret = true;
                    break;
                }
            }
            if(ret)
                break;
        }
        return n;
    }
    
    public void setColor(int _col){
        color = _col;
        blocktexture = textures[color-1];
    }
    public void speedUp(){
        velocity = velocity/2;
    }
    
    public void speedDown(){
        velocity = velocity * 2;
    }
    
    public void setAsShadow(){
        textures[0] = new Texture (Gdx.files.internal("block-red2.bmp"));
        textures[1] = new Texture (Gdx.files.internal("block-blue2.bmp"));
    }
}

