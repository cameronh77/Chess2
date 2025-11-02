package moves;

import layout.Board;
import pieces.Piece;

import java.awt.datatransfer.Clipboard;

public class Move {
    private int oldX, oldY;

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    private int newX, newY;
    private Board board;
    private Piece capturedPiece;

    public Move(Piece piece, int newX, int newY, Board board){
        this.oldX = piece.getXord();
        this.oldY = piece.getYord();
        this.newX = newX;
        this.newY = newY;
        this.board = board;
    }






}
