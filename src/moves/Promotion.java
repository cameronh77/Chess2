package moves;

import layout.Board;
import pieces.Castle;
import pieces.Piece;
import pieces.Queen;

public class Promotion extends Move{

    private Piece newQueen;

    public Promotion(Piece piece, int newX, int newY, Board board){
        super(piece, newX, newY, board);

    }

    @Override
    public void execute(){
        board.getTiles().get(oldX).get(oldY).setPiece(null);

        if(board.getTiles().get(newX).get(newY).getPiece() != null){
            capturedPiece = board.getTiles().get(newX).get(newY).getPiece();
            board.getPieces().remove(capturedPiece);
        }
        newQueen = new Queen(newX, newY, movingPiece.isWhite(), board.getTileSize());
        board.getPieces().remove(movingPiece);
        board.getPieces().add(newQueen);

        board.getTiles().get(newX).get(newY).setPiece(newQueen);

        newQueen.setIsFirstMove(false);
    }
}
