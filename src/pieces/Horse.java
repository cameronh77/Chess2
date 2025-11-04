package pieces;

import layout.Board;
import moves.Move;

import java.util.ArrayList;

public class Horse extends Piece{

    public Horse(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "horse", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board){

        ArrayList<Move> moves = new ArrayList<>();

        moves.add(new Move(this, xord/size+1, yord/size +2, board));
        moves.add(new Move(this, xord/size+1, yord/size -2, board));
        moves.add(new Move(this, xord/size-1, yord/size +2, board));
        moves.add(new Move(this, xord/size-1, yord/size -2, board));

        moves.add(new Move(this, xord/size+2, yord/size +1, board));
        moves.add(new Move(this, xord/size+2, yord/size -1, board));
        moves.add(new Move(this, xord/size-2, yord/size +1, board));
        moves.add(new Move(this, xord/size-2, yord/size -1, board));

        ArrayList<Move> trueMoves = new ArrayList<>();

        for(Move move: moves){
            if(move.getNewX() >= 0 && move.getNewX() < board.boardX && move.getNewY() >= 0 && move.getNewY() < board.boardY){
                if(!(board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece() != null && board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece().getIsWhite() == isWhite)){
                    trueMoves.add(move);
                }
            }
        }
        return trueMoves;
    }

}
