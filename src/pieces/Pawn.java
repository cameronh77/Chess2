package pieces;

import layout.Board;
import moves.EnPassant;
import moves.Move;
import moves.Promotion;

import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "pawn", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board, Boolean ignoreCheck){
        ArrayList<Move> moves = new ArrayList<>();
        if(isWhite == board.getWhiteToMove()) {
            int offset = isWhite ? -1 : 1; //Set offset
            //Generate moves for first turn not considering promotion on first turn, can revisit
            if (this.isFirstMove) {
                if (board.getTiles().get(xord / size).get(yord / size + (2 * offset)).getPiece() == null && board.getTiles().get(xord / size).get(yord / size + (offset)).getPiece() == null) {
                    moves.add(new Move(this, xord / size, yord / size + (2 * offset), board));
                }
            }
            //Generate single moves
            if (board.getTiles().get(xord / size).get(yord / size + (offset)).getPiece() == null) {
                if (yord / size + (offset) == (isWhite ? 0 : board.boardX - 1)) {
                    moves.add(new Promotion(this, xord / size, yord / size + (offset), board));
                }
                moves.add(new Move(this, xord / size, yord / size + (offset), board));
            }

            //Generate left takes (This could probably be more efficient)
            if (xord / size - 1 >= 0 && board.getTiles().get(xord / size - 1).get(yord / size + (offset)).getPiece() != null && board.getTiles().get(xord / size - 1).get(yord / size + (offset)).getPiece().getIsWhite() != isWhite) {
                if (yord / size + (offset) == (isWhite ? 0 : board.boardX - 1)) {
                    moves.add(new Promotion(this, xord / size - 1, yord / size + (offset), board));
                }
                moves.add(new Move(this, xord / size - 1, yord / size + (offset), board));
            }

            //Generate right takes
            if (xord / size + 1 < board.boardX && board.getTiles().get(xord / size + 1).get(yord / size + (offset)).getPiece() != null && board.getTiles().get(xord / size + 1).get(yord / size + (offset)).getPiece().getIsWhite() != isWhite) {
                if (yord / size + (offset) == (isWhite ? 0 : board.boardX - 1)) {
                    moves.add(new Promotion(this, xord / size + 1, yord / size + (offset), board));
                }
                moves.add(new Move(this, xord / size + 1, yord / size + (offset), board));
            }

            if (board.getEnPassantTile() != null) {
                //Generate left en passant
                if (xord / size - 1 == board.getEnPassantTile()[0] && yord / size + (offset) == board.getEnPassantTile()[1]) {
                    moves.add(new EnPassant(this, xord / size - 1, yord / size + (offset), board));
                }

                //Generate left en passant
                if (xord / size + 1 == board.getEnPassantTile()[0] && yord / size + (offset) == board.getEnPassantTile()[1]) {
                    moves.add(new EnPassant(this, xord / size + 1, yord / size + (offset), board));
                }
            }
            if(!ignoreCheck){
                //Ignore the naming convention for now
                ArrayList<Move> trueMoves = new ArrayList<>();
                for (Move move : moves) {
                    if (!board.checkEvaluator(move)) {
                        trueMoves.add(move);
                    }
                }
                return trueMoves;
            }
            return moves;
        } else {
            return moves;
        }
    }

}
