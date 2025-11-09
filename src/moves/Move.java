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
    protected int[] enPassantTile;


    public Move(Piece piece, int newX, int newY, Board board){
        this.oldX = piece.getCol();
        this.oldY = piece.getRow();
        this.newX = newX;
        this.newY = newY;
        this.board = board;
        this.movingPiece = piece;
        this.isFirstMove = piece.getIsFirstMove();

        if(newX < board.boardX && newY < board.boardY && newX >= 0 && newY >= 0 && board.getTiles().get(newX).get(newY).getPiece() != null){
            capturedPiece = board.getTiles().get(newX).get(newY).getPiece();
        } else{
            capturedPiece = null;
        }

        this.enPassantTile = board.getEnPassantTile();
    }

    public void execute(){
        //Vacate old piece
        board.getTiles().get(oldX).get(oldY).setPiece(null);

        //Remove captured piece from the board
        board.getPieces().remove(capturedPiece);

        //Add the piece being moved to the new tile
        board.getTiles().get(newX).get(newY).setPiece(movingPiece);

        //Set the moving pieces location
        movingPiece.setCol(newX);
        movingPiece.setRow(newY);

        //Alter first move state
        movingPiece.setIsFirstMove(false);

        //Set en passant tile
        if(movingPiece.getName() == "pawn" && Math.abs(oldY-newY)==2){
            int[] enPassantTile = {newX, (oldY+newY)/2};
            board.setEnPassantTile(enPassantTile);
        } else{
            board.setEnPassantTile(null);
        }

        //Flip the turn
        board.setWhiteToMove(!board.getWhiteToMove());
    }


    public void undo(){
        //Revert Tile ownership
        board.getTiles().get(newX).get(newY).setPiece(capturedPiece);

        //Re add captured pieces to the board
        if(capturedPiece != null) {
            board.getPieces().add(capturedPiece);
        }

        //Set the piece ownership back to old tile
        board.getTiles().get(oldX).get(oldY).setPiece(movingPiece);

        //Revert piece co ordinates to original location
        movingPiece.setCol(oldX);
        movingPiece.setRow(oldY);

        //Revert first move status
        movingPiece.setIsFirstMove(isFirstMove);

        //Revert en passant tile
        board.setEnPassantTile(enPassantTile);

        //Flip the turn
        board.setWhiteToMove(!board.getWhiteToMove());
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

    public int getOldX(){
        return oldX;
    }

    public int getOldY(){
        return oldY;
    }

    public Piece getCapturedPiece(){
        return capturedPiece;
    }
}
