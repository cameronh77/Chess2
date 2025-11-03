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
        if(xord-1 >= 0){
            moves.add(new Move(this, xord-1, yord, board));
            if(yord-1 >= 0){
                moves.add(new Move(this, xord, yord-1, board));
                moves.add(new Move(this, xord-1, yord-1, board));
            }
            if(yord + 1 < board.boardY){
                moves.add(new Move(this, xord-1, yord+1, board));
            }
        }

        if(xord+1 < board.boardX){
            moves.add(new Move(this, xord+1, yord, board));
            if(yord+1 < board.boardX){
                moves.add(new Move(this, xord, yord+1, board));
                moves.add(new Move(this, xord+1, yord+1, board));
            }
            if(yord - 1 >= 0){
                moves.add(new Move(this, xord-1, yord-1, board));
            }
        }

        //still needs castling
        return moves;
    }

}
