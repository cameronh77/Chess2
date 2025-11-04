package pieces;

import layout.Board;
import moves.Move;

import java.util.ArrayList;

public class Horse extends Piece{

    public Horse(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "horse", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board, Boolean ignoreCheck){

        ArrayList<Move> moves = new ArrayList<>();

        if(isWhite == board.getWhiteToMove()) {
            moves.add(new Move(this, xord / size + 1, yord / size + 2, board));
            moves.add(new Move(this, xord / size + 1, yord / size - 2, board));
            moves.add(new Move(this, xord / size - 1, yord / size + 2, board));
            moves.add(new Move(this, xord / size - 1, yord / size - 2, board));

            moves.add(new Move(this, xord / size + 2, yord / size + 1, board));
            moves.add(new Move(this, xord / size + 2, yord / size - 1, board));
            moves.add(new Move(this, xord / size - 2, yord / size + 1, board));
            moves.add(new Move(this, xord / size - 2, yord / size - 1, board));

            ArrayList<Move> trueMoves = new ArrayList<>();

            for (Move move : moves) {
                if (move.getNewX() >= 0 && move.getNewX() < board.boardX && move.getNewY() >= 0 && move.getNewY() < board.boardY) {
                    if (!(board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece() != null && board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece().getIsWhite() == isWhite)) {
                        trueMoves.add(move);
                    }
                }
            }

            if(!ignoreCheck){
                //Ignore the naming convention for now
                ArrayList<Move> truerMoves = new ArrayList<>();
                for (Move move : trueMoves) {
                    if (!board.checkEvaluator(move)) {
                        truerMoves.add(move);
                    }
                }
                return truerMoves;
            }


            return trueMoves;
        }
        else{
            return moves;
        }
    }

}
