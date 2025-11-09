package moves;

import layout.Board;
import pieces.*;

public class Promotion extends Move{

    private Piece promotionPiece;
    private Pieces type;

    public Promotion(Piece piece, int newX, int newY, Board board, Pieces type){
        super(piece, newX, newY, board);
        this.type = type;

    }

    @Override
    public void execute(){
        board.getTiles().get(oldX).get(oldY).setPiece(null);

        board.getPieces().remove(capturedPiece);
        if(type == Pieces.QUEEN){
            promotionPiece = new Queen(newX, newY, movingPiece.isWhite(), board.getTileSize());
        } else if (type == Pieces.BISHOP){
            promotionPiece = new Bishop(newX, newY, movingPiece.isWhite(), board.getTileSize());
        }else if (type == Pieces.HORSE){
            promotionPiece = new Horse(newX, newY, movingPiece.isWhite(), board.getTileSize());
        }else if (type == Pieces.CASTLE){
            promotionPiece = new Castle(newX, newY, movingPiece.isWhite(), board.getTileSize());
        }

        board.getPieces().remove(movingPiece);
        board.getPieces().add(promotionPiece);

        board.getTiles().get(newX).get(newY).setPiece(promotionPiece);

        promotionPiece.setIsFirstMove(false);

        board.setWhiteToMove(!board.getWhiteToMove());
    }

    @Override
    public void undo(){
        //Re add initial pawn to tile
        board.getTiles().get(oldX).get(oldY).setPiece(movingPiece);

        //Re add the captured piece
        if(capturedPiece != null){
            board.getPieces().add(capturedPiece);
        }


        board.getPieces().add(movingPiece);
        board.getPieces().remove(promotionPiece);

        board.getTiles().get(newX).get(newY).setPiece(capturedPiece);

        board.setWhiteToMove(!board.getWhiteToMove());
    }
}
