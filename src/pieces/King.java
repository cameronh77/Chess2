package pieces;

import layout.Board;
import moves.Castling;
import moves.Move;

import java.util.ArrayList;

public class King extends Piece{

    public King(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "king", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board, Boolean ignoreCheck){
        ArrayList<Move> moves = new ArrayList<>();
        if(isWhite == board.getWhiteToMove()) {
            //Currently no moves are showing and this setup also means king can take pieces of its own colour
            if (xord / size - 1 >= 0) {
                moves.add(new Move(this, xord / size - 1, yord / size, board));
                if (yord / size - 1 >= 0) {
                    moves.add(new Move(this, xord / size, yord / size - 1, board));
                    moves.add(new Move(this, xord / size - 1, yord / size - 1, board));
                }
                if (yord / size + 1 < board.boardY) {
                    moves.add(new Move(this, xord / size - 1, yord / size + 1, board));
                }
            }

            if (xord / size + 1 < board.boardX) {
                moves.add(new Move(this, xord / size + 1, yord / size, board));
                if (yord / size + 1 < board.boardX) {
                    moves.add(new Move(this, xord / size, yord / size + 1, board));
                    moves.add(new Move(this, xord / size + 1, yord / size + 1, board));
                }
                if (yord - 1 >= 0) {
                    moves.add(new Move(this, xord / size + 1, yord / size - 1, board));
                }
            }


            //Castling is being restricted to 8x8 boards
            if (isFirstMove && board.boardX == 8 && board.boardY == 8) {
                Piece rightCastle = board.getTiles().get(7).get(isWhite ? 7 : 0).getPiece();
                //I refuse to believe that there isn't a more efficient way to do this
                if (rightCastle != null && rightCastle.getIsFirstMove() && board.getTiles().get(6).get(isWhite ? 7 : 0).getPiece() == null && board.getTiles().get(5).get(isWhite ? 7 : 0).getPiece() == null && rightCastle.getName() == "castle") {
                    moves.add(new Castling(this, 6, isWhite ? 7 : 0, board, rightCastle));
                }

                Piece leftCastle = board.getTiles().get(0).get(isWhite ? 7 : 0).getPiece();
                //I refuse to believe that there isn't a more efficient way to do this
                if (leftCastle != null && leftCastle.getIsFirstMove() && board.getTiles().get(1).get(isWhite ? 7 : 0).getPiece() == null && board.getTiles().get(2).get(isWhite ? 7 : 0).getPiece() == null && board.getTiles().get(3).get(isWhite ? 7 : 0).getPiece() == null && leftCastle.getName() == "castle") {
                    moves.add(new Castling(this, 2, isWhite ? 7 : 0, board, leftCastle));
                }
            }

            ArrayList<Move> trueMoves = new ArrayList<>();
            for (Move move : moves) {
                if (!(board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece() != null && board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece().getIsWhite() == isWhite)) {
                    trueMoves.add(move);
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
