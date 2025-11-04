package pieces;

import layout.Board;
import moves.Move;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Piece {
    protected BufferedImage image;
    protected String name;
    protected Boolean isFirstMove = true;

    public int getXord() {
        return xord;
    }

    public void setXord(int xord) {
        this.xord = xord;
    }

    public int getYord() {
        return yord;
    }

    public void setYord(int yord) {
        this.yord = yord;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    protected int xord;
    protected int yord;

    protected int col, row;
    protected boolean isWhite;
    protected int size;

    Piece(int x, int y, boolean isWhite, String name, int size){
        this.col = x;
        this.row = y;
        this.xord = x*size;
        this.yord = y*size;
        this.isWhite = isWhite;
        this.name = name;
        this.size = size;
        try {
            // Load the image from your resources folder (e.g., src/main/resources)
            String path = "resources/pieces/"+(isWhite?"white":"black")+"-"+name+".png";
            System.out.println(path);
            image = ImageIO.read(new File(path));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("Failed to load piece image.");
        }

    }

    public void draw(Graphics2D g){
        g.drawImage(image, xord, yord, size, size,  null);
    }

    public ArrayList<Move> generateMoves(Board board){
        return null;
    }

    public void setIsFirstMove(Boolean state){
        isFirstMove = state;
    }

    public Boolean getIsFirstMove(){
        return isFirstMove;
    }

    public Boolean getIsWhite(){
        return isWhite;
    }

    public void setCol(int col){
        this.col = col;
        this.xord = col*size;
    }
    public int getCol(){
        return col;
    }

    public void setRow(int row){
        this.row = row;
        this.yord = row*size;
    }

    public int getRow(){
        return row;
    }


}
