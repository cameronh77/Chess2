package moves;

import layout.Board;
import pieces.Castle;
import pieces.Piece;
import pieces.Queen;

public class EnPassant extends Move{

    private int[] enPassantTile;

    public EnPassant(Piece piece, int newX, int newY, Board board){
        super(piece, newX, newY, board);

    }

    @Override
    public void execute(){

        enPassantTile = board.getEnPassantTile();
        board.getTiles().get(oldX).get(oldY).setPiece(null);

        capturedPiece = board.getTiles().get(newX).get(newY+(movingPiece.getIsWhite()?1:-1)).getPiece();
        board.getTiles().get(newX).get(newY+(movingPiece.getIsWhite()?1:-1)).setPiece(null);
        board.getPieces().remove(capturedPiece);


        board.getTiles().get(newX).get(newY).setPiece(movingPiece);

        movingPiece.setCol(newX);
        movingPiece.setRow(newY);


        board.setEnPassantTile(null);

    }
}
