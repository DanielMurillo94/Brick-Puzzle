package com.danielmurillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.Random;

/**
 *
 * @author Daniel
 * The table where the squares and the pieces are seen
 * There is one instance for the player and another for the CPU
 */
public class Column {
    public int columnIndex;//The equivalent to the index in an array
    public Vector2 position;//The position of the column
    public float height,width;//The height and widht
    public int[][] table;//The array where the blocks of this column will be saved
    public int color;
    public float unitX;//The lenght of the squares in pixels
    public float unitY;
    public int maxHeight;//The index of the highest block at the moment
    public boolean gameOver;
    public int score;
    
    public Column leftC;
    public Column rightC;
    
    public Piece[] piece;
    public Piece shadow;
    
    private Texture[] back;
            
    
    Random ran;
    
    static int RED = 1, BLUE = 2, GREEN = 3;
    
    public Column(float positionX, float positionY, float _height, float _width, String _color){
        position = new Vector2(positionX, positionY);
        table = new int[10][20];
        height = _height;
        width = _width;
        unitX = width/table.length;
        unitY = height/table[0].length;
        back = new Texture[2];
        if(_color == "red"){
            color = 1;
            back[0] = new Texture (Gdx.files.internal("back-red.bmp"));
            back[1] = new Texture (Gdx.files.internal("back-red2.bmp"));
        }
        else if(_color == "blue"){
            color = 2;
            back[0] = new Texture (Gdx.files.internal("back-blue.bmp"));
            back[1] = new Texture (Gdx.files.internal("back-blue2.bmp"));
        }
        else if (_color == "green"){
            color = 3;
            back[0] = new Texture (Gdx.files.internal("back-green.bmp"));
            back[1] = new Texture (Gdx.files.internal("back-green2.bmp"));
        }
        piece = new Piece[2];
        piece[0] = new Piece(this,unitX, unitY);
        piece[1] = new Piece(this,unitX, unitY);
        shadow = new Piece(this,unitX, unitY);//Create the object shadow
        shadow.active = piece[0].active;//Give it the same piece to draw
        shadow.setAsShadow();
        maxHeight = 0;
        gameOver = false;
        ran = new Random();
        score = 0;
    }
    
    //update the piece, in the case that the piece landed, it will return true
    public boolean update(float delta){
        boolean placedDown = false;
        if(piece != null){
            if(piece == piece[0])
                projectShadow(_piece);
            if (_piece.update(delta)){//Tries to update the piece, in the case that its time to move it down the function will return true
                placedDown = putInTable(_piece);
            }
        }
        return placedDown;
    }
    
    public boolean putInTable(Piece _piece){
        score += 1;
        int[][] arr = _piece.active.figure;//We take the array of the figure
        if(!movePiece(_piece,arr,0,-1)){//We try to move it down 1 block, if we can't is because it's time to land it
            if(_piece.color == color){//If the piece is of the same color of the table
                score += 10;
                for(int i = 0; i < arr.length; i++){
                    for(int j = 0; j< arr[0].length;j++){//Cycle every block in the array of the figure
                        if(arr[i][j] != 0){//Whenever is not empty, it will save it on the table array
                            if(_piece.position.y + j >= table[0].length){ //If the piece placed is above the vertical limit
                                gameOver = true;
                                return true;
                            }
                            else{
                                table[(int)_piece.position.x + i][(int)_piece.position.y + j] = _piece.color;
                            }
                        }
                    }
                }
            }
            else{//If the piece color is different to the table color
                int nh = 0;
                int no = 0;
                for(int i = 0; i < arr.length; i++){
                    for(int j = 0; j< arr[0].length;j++){//Cycle every block in the array of the figure
                        if(arr[i][j] != 0){//Whenever is not empty
                            if(_piece.position.y+j < table.length){
                                boolean keep = true;
                                while(keep){
                                    if(ran.nextInt(2) == 0 && nh < 2){
                                        table[(int)_piece.position.x + i][(int)_piece.position.y + j] = _piece.color;
                                        nh++;
                                        keep = false;
                                    }
                                    else if(no < 2){
                                        if(no == 0)
                                            penalty(rightC);
                                        if(no == 1)
                                            penalty(leftC);
                                        no++;
                                        keep = false;
                                    }
                                }
                            }
                            else{
                                gameOver = true;
                                return true;
                            }
                        }
                    }
                }
            }
            _piece.resetPiece();//Once we landed, we take back our piece and return it to the center and give a new form
            checkLines();//Check lines to see if there were some lines to delete
            return true;
        }
        return false;
    }
    
    public void render(float delta, SpriteBatch batch, Texture[] textures){
        //Draws the background
        for(int i = 0; i < table.length;i++){
            for (int j = 0; j < table[0].length;j++){
                if(j <= maxHeight)//Here draws with one type of tile to indicate when you cannot switch to this column
                    batch.draw(back[1],position.x + i*unitX, position.y +j*unitY, unitX, unitY);
                else//Here draws with another to indicate when you can switch to this column
                    batch.draw(back[0],position.x + i*unitX, position.y +j*unitY, unitX, unitY);
            }
        }
        //Draws the blocks in the column
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[0].length; j++){
                if(table[i][j] != 0){
                    batch.draw(textures[table[i][j] - 1], position.x + i*unitX, position.y+j*unitY, unitX, unitY);
                }
            }
        }
        //Draws the piece that is falling
        if(piece[0] != null){            
            shadow.render(delta,batch, this);
            piece[0].render(delta, batch, this);
        }
        if(piece[1] != null)
            piece[1].render(delta, batch, this);
    }
    
    //Tries to move the piece, if the movement was done returns true, in the case that the movement could not be done, returns false
    public boolean movePiece(Piece _piece,int[][] matrix,int cx, int cy){
            //In case that is valid, it moves
            if(canMove(_piece,matrix,cx,cy)){
                _piece.position.x += cx;
                _piece.position.y += cy;
                return true;
            }            
            return false;
    }
    
    //Checks if the matrix can move the number of blocks indicated by cx and cy relative to its actual position
    public boolean canMove(Piece _piece,int[][] matrix,int cx, int cy){
        boolean canupdate = true;
        int nx, ny;
        //Asks for every block of the piece if the place is going to move is valid
        for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j< matrix[0].length;j++){
                    if(matrix[i][j] != 0){ //If the block that is checking is not empty
                        nx = (int)_piece.position.x + i + cx;
                        ny = (int)_piece.position.y + j + cy;
                        if(nx >= table.length || nx < 0 ){//Checks if is outside the play area in the x axis
                            canupdate = false;
                        }
                        else if(ny < 20){//Checks if is inside the play area in the y axis
                            if(ny < 0 || nx < 0 || nx >= table.length  || table[nx][ny] != 0){//Checks if is inside the play area and if is on accupied space
                                canupdate = false;
                            }   
                        }
                    }
                }
            }
        return canupdate;
    }
    
    //Method for checking if there are some rows that are full and need to be eliminated
    public void checkLines(){
        for(int j = 0; j < table[0].length; j++){
            int total = 0;//This will save the number of blocks with the color of the column
            int emptysquares = 0;//This will save the number of emtpty blocks
            for(int i = 0; i<table.length;i++){//This cycle goes through one row
                if(table[i][j] == color)
                    total++;
                else if(table[i][j] == 0)
                    emptysquares++;
            }
            if(total == table.length){
                deleteLine(j);//if the row was full, deletes it
                removePenalty();//Remove 1 penalty
                j--;//we need to extract 1 from j because when the row is deleted, everything that is above that line goes down one line
                score += 100;
            }
            if (emptysquares == table.length){
                maxHeight = j - 1;//if the line is empty, that means that there are no more blocks, so, the masimun height was just reached
                break;
            }
        }
    }
    
    public void removePenalty(){
        int in = 300;
        for(int j = table[0].length - 1; j >= 0; j--){
            for(int i = 0; i < table.length; i++){
                if(table[i][j] != 0 && table[i][j] != color){
                    if(in == 300)
                        in = i;
                    if(ran.nextInt(2) == 0)
                        in = i;
                }
            }
            if(in != 300){
                table[in][j] = 0;
                break;
            }
        }
    }
    
    public void removePenalty(Vector3 vect){
        if(vect.x > position.x && vect.x < position.x + width && vect.y > position.y && vect.y < position.y + height){
            int px = (int)((vect.x - position.x)/unitX);
            int py = (int)((vect.y - position.y)/unitY);
            if(table[px][py]!= color)
                table[px][py] = 0;
        }
    }
    
    public void deleteLine(int index){
        //This cycle goes through the row and makes every block = 0
        for(int i = 0; i < table.length; i++){
            table[i][index] = 0;
        }
        //this cycle goes through all the rows above the index and makes them go down 1 block
        for(int j = index; j < table[0].length - 1; j++){
            for(int i = 0; i<table.length;i++){
                table[i][j] = table[i][j+1];
                table[i][j+1] = 0;
            }
        }
    }
    
    public void setColor(ShapeRenderer shape, int col){
        switch(col){
            case 1:
                shape.setColor(1, 0.2f, 0.2f, 1);
                break;
            case 2:
                shape.setColor(0.2f, 1, 0.2f, 1);
                break;
            case 3:
                shape.setColor(0.2f, 0.2f, 1, 1);
                break;
        }
    }
    
    //Puts one block of the color of this column in the column parameter
    public void penalty(Column c){
        score -= 50;
        int[] checked = new int[c.table.length];//Array to keep track of the columns that have been already checked
        boolean keep = true;
        while(keep && contain(checked,0)){//As long as there is still one column without check and the block has not been placed
            int n = ran.nextInt(c.table.length);
            if(checked[n] == 0){//takes one random column and if it has not been checked
                for(int j = c.table[n].length - 1;j >= 0; j--){//travel the array from up to down
                    if(c.table[n][j] == 0 && (j == 0 || c.table[n][j-1] != 0)){//if it finds out that there is one block down or it is in the bottom then fills it 
                        c.table[n][j] = color;
                        keep = false;
                        break;
                    }
                }
                checked[n] = 1;
            }
        }
    }
    
    private boolean contain(int[] arr, int val){
        for(int i = 0;i < arr.length - 1; i++){
            if(arr[i] == val)
                return true;
        }
        return false;
    }
    
    public void moveRight(int index){
        movePiece(piece[index],piece[index].active.figure,1,0);
    }
    
    public void moveLeft(int index){
        movePiece(piece[index],piece[index].active.figure,-1,0);
    }
    
    public void rotateRight(int index){
        if(canMove(piece[index],piece[index].active.right.figure,0,0))
        piece[0].active = piece[0].active.right;
    }
    
    public void rotateLeft(int index){
        //if(canRotate(false))
        if(canMove(piece[index],piece[index].active.left.figure,0,0))
        piece[0].active = piece[0].active.left;
    }
    
    public void projectShadow(Piece _piece){
        shadow.active = _piece.active;
        shadow.position.x = _piece.position.x;
        shadow.position.y = _piece.position.y;
        shadow.setColor(_piece.color);
        for(int i = (int) shadow.position.y; i >= -1; i --){
            if(canMove(shadow,shadow.active.figure,0,-1))
                shadow.position.y -= 1;
            else{
                break;
            }
        }
    }
}
