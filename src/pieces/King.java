package pieces;

import layout.Board;
import moves.Move;

import java.util.ArrayList;

public class King extends Piece{

    public King(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "king", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();
        //Currently no moves are showing and this setup also means king can take pieces of its own colour
        if(xord/size-1 >= 0){
            moves.add(new Move(this, xord/size-1, yord/size, board));
            if(yord/size-1 >= 0){
                moves.add(new Move(this, xord/size, yord/size-1, board));
                moves.add(new Move(this, xord/size-1, yord/size-1, board));
            }
            if(yord/size + 1 < board.boardY){
                moves.add(new Move(this, xord/size-1, yord/size+1, board));
            }
        }

        if(xord/size+1 < board.boardX){
            moves.add(new Move(this, xord/size+1, yord/size, board));
            if(yord/size+1 < board.boardX){
                moves.add(new Move(this, xord/size, yord/size+1, board));
                moves.add(new Move(this, xord/size+1, yord/size+1, board));
            }
            if(yord - 1 >= 0){
                moves.add(new Move(this, xord/size+1, yord/size-1, board));
            }
        }

        ArrayList<Move> trueMoves = new ArrayList<>();
        for(Move move: moves){
            if(!(board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece() != null && board.getTiles().get(move.getNewX()).get(move.getNewY()).getPiece().getIsWhite() == isWhite)){
                trueMoves.add(move);
            }
        }


        //still needs castling
        return trueMoves;
    }

}
