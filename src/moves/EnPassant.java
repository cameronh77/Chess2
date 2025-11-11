package moves;

import layout.Board;
import pieces.Castle;
import pieces.Piece;
import pieces.Queen;

public class EnPassant extends Move{

    public EnPassant(Piece piece, int newX, int newY, Board board){
        super(piece, newX, newY, board);
        capturedPiece = board.getTiles().get(newX).get(newY+(movingPiece.getIsWhite()?1:-1)).getPiece();
    }

    @Override
    public void execute(){
        board.getTiles().get(oldX).get(oldY).setPiece(null);

        board.getTiles().get(newX).get(newY+(movingPiece.getIsWhite()?1:-1)).setPiece(null);
        board.getPieces().remove(capturedPiece);


        board.getTiles().get(newX).get(newY).setPiece(movingPiece);

        movingPiece.setCol(newX);
        movingPiece.setRow(newY);


        board.setEnPassantTile(null);

        board.setWhiteToMove(!board.getWhiteToMove());
    }

    @Override
    public void undo(){
        //Move the pawn back
        board.getTiles().get(oldX).get(oldY).setPiece(movingPiece);

        //Re add the captured pawn
        if(capturedPiece != null){
            board.getTiles().get(newX).get(newY+(movingPiece.getIsWhite()?1:-1)).setPiece(capturedPiece);
            board.getPieces().add(capturedPiece);
        }


        //Clear the new tile
        board.getTiles().get(newX).get(newY).setPiece(null);

        //Draw pawn on old col/row
        movingPiece.setCol(oldX);
        movingPiece.setRow(oldY);

        //Reset en passant tile
        board.setEnPassantTile(enPassantTile);

        board.setWhiteToMove(!board.getWhiteToMove());
    }
}
