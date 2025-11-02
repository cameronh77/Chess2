package layout;

import pieces.Piece;

import java.awt.*;

public class Tile {
    private int xord;
    private int yord;
    private int size;
    private Piece piece;
    Tile(int xord, int yord, int size){
        this.xord=xord;
        this.yord=yord;
        this.size = size;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor((xord+yord)%2==0?Color.BLUE:Color.GREEN);
        g2d.fillRect(xord*size, yord*size, size, size);
    }

    public int getXord() {
        return xord;
    }

    public int getYord(){
        return yord;
    }

    public Piece getPiece(){
        return piece;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }
}
