package moves;

import layout.Board;
import pieces.Castle;
import pieces.Piece;

public class Castling extends Move{

    private Piece castle;
    public Castling(Piece piece, int newX, int newY, Board board, Piece castle){
        super(piece, newX, newY, board);
        this.castle = castle;
    }

    @Override
    public void execute(){
        board.getTiles().get(oldX).get(oldY).setPiece(null);
        board.getTiles().get(castle.getCol()).get(castle.getRow()).setPiece(null);

        board.getTiles().get(newX).get(newY).setPiece(movingPiece);
        board.getTiles().get(castle.getCol()==7?5:3).get(castle.getRow()).setPiece(castle);

        movingPiece.setCol(newX);
        movingPiece.setRow(newY);

        castle.setCol(castle.getCol()==7?5:3);
        castle.setRow(castle.getRow());


        movingPiece.setIsFirstMove(false);
        castle.setIsFirstMove(false);

        board.setWhiteToMove(!board.getWhiteToMove());
    }

    @Override
    public void undo(){
        //Move the kind and castle back
        board.getTiles().get(oldX).get(oldY).setPiece(movingPiece);
        board.getTiles().get(castle.getCol()).get(castle.getRow()).setPiece(castle);

        //Clear new tiles
        board.getTiles().get(newX).get(newY).setPiece(null);
        board.getTiles().get(castle.getCol()==7?5:3).get(castle.getRow()).setPiece(null);

        //Set king old cords
        movingPiece.setCol(oldX);
        movingPiece.setRow(oldY);

        //Set castle old cords
        castle.setCol(castle.getCol()==5?7:0);
        castle.setRow(castle.getRow());

        //Revert first move status
        movingPiece.setIsFirstMove(isFirstMove);
        castle.setIsFirstMove(isFirstMove);

        board.setEnPassantTile(enPassantTile);

        board.setWhiteToMove(!board.getWhiteToMove());
    }
}
