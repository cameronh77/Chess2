package moves;

import layout.Board;
import pieces.Piece;

import java.awt.datatransfer.Clipboard;

public class Move {
    protected int oldX, oldY;
    protected int newX, newY;
    protected Board board;
    protected Piece movingPiece;
    protected Piece capturedPiece;
    protected Boolean isFirstMove;


    public Move(Piece piece, int newX, int newY, Board board){
        this.oldX = piece.getCol();
        this.oldY = piece.getRow();
        this.newX = newX;
        this.newY = newY;
        this.board = board;
        this.movingPiece = piece;
        this.isFirstMove = piece.getIsFirstMove();
    }

    public void execute(){
        board.getTiles().get(oldX).get(oldY).setPiece(null);
        if(board.getTiles().get(newX).get(newY).getPiece() != null){
            capturedPiece = board.getTiles().get(newX).get(newY).getPiece();
            board.getPieces().remove(capturedPiece);
        }
        board.getTiles().get(newX).get(newY).setPiece(movingPiece);
        movingPiece.setCol(newX);
        movingPiece.setRow(newY);

        movingPiece.setIsFirstMove(false);

        if(movingPiece.getName() == "pawn" && Math.abs(oldY-newY)==2){
            int[] enPassantTile = {newX, (oldY+newY)/2};
            board.setEnPassantTile(enPassantTile);
        } else{
            board.setEnPassantTile(null);
        }
    }

    public void undo(){

    }

    public Piece getMovingPiece(){
        return movingPiece;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }







}
