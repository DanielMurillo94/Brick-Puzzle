/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielmurillo;

import static com.badlogic.gdx.Gdx.input;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


/**
 *
 * @author Daniel
 */
public class MyInputProcessor implements InputProcessor{
    
    Columns columns;
    int xAxis;
    OrthographicCamera camera;
    
    public MyInputProcessor(Columns _columns, OrthographicCamera _cam){
        columns = _columns;
        xAxis = 0;
        camera = _cam;
    }

    @Override
    public boolean keyDown(int keycode) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(columns.gameOver == false){
            if(keycode == Input.Keys.RIGHT)
                columns.columns[0].moveRight(0);
            else if(keycode == Input.Keys.LEFT)
                columns.columns[0].moveLeft(0);
            else if (keycode == Input.Keys.DOWN){
                columns.columns[0].piece[0].speedUp();
            }
            else if(keycode == Input.Keys.A){
                columns.columns[0].rotateLeft(0);
            }
            else if(keycode == Input.Keys.D){
                columns.columns[0].rotateRight(0);
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(columns.gameOver == false)
            if (keycode == Input.Keys.DOWN)
                columns.columns[0].piece[0].speedDown();
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         /*if(character == Input.Keys.RIGHT && xAxis == 0){
            xAxis = 1;
            column.moveRight();
        }
        else if(character == Input.Keys.LEFT && xAxis == 0){
            xAxis = -1;
            column.moveLeft();
        }*/
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Vector3 touchPos = new Vector3(screenX, screenY, 0);
        camera.unproject(touchPos);
        //columns.removePenalty(touchPos);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
