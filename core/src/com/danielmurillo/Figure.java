/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielmurillo;

/**
 *
 * @author Daniel
 */
public class Figure {
    
    int [][] figure;
    Figure right;
    Figure left;
    
    public Figure(int[][] _figure){
        figure = _figure;
    }
    
    public void setRight(Figure _fr){
        right = _fr;
    }
    
    public void setLeft(Figure _fl){
        left = _fl;
    }
    
    public void createFigure(String type){
        if(type == "O")
        {
            figure[1][1] = 1;
            figure[1][2] = 1;
            figure[2][1] = 1;
            figure[2][2] = 1;
        }
        else if(type == "I")
        {
            figure[1][0] = 1;
            figure[1][1] = 1;
            figure[1][2] = 1;
            figure[1][3] = 1;
        }
        else if(type == "S"){
            figure[0][1] = 1;
            figure[1][1] = 1;
            figure[1][2] = 1;
            figure[2][2] = 1;
        }
        else if (type == "Z"){
            figure[1][1] = 1;
            figure[1][2] = 1;
            figure[2][1] = 1;
            figure[0][2] = 1;
           
        }
        else if (type == "L"){
            figure[1][0] = 1;
            figure[1][1] = 1;
            figure[1][2] = 1;
            figure[2][0] = 1;
        }
        else if (type == "J"){
            figure[1][0] = 1;
            figure[1][1] = 1;
            figure[1][2] = 1;
            figure[0][0] = 1;
        }
        else if (type == "T"){
            figure[0][1] = 1;
            figure[1][1] = 1;
            figure[2][1] = 1;
            figure[1][0] = 1;
        }
    }
}
