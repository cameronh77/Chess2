package pieces;

import layout.Board;
import moves.Move;

import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "pawn", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();
        int offset = isWhite?-1:1; //Set offset
        //Generate moves for first turn
        if(this.isFirstMove){
            if(board.getTiles().get(xord/size).get(yord/size+(2*offset)).getPiece() == null && board.getTiles().get(xord/size).get(yord/size+(offset)).getPiece() == null){
                moves.add(new Move(this, xord/size, yord/size+(2*offset), board));
            }
        }
        //Generate single moves
        if(board.getTiles().get(xord/size).get(yord/size+(offset)).getPiece() == null){
            moves.add(new Move(this, xord/size, yord/size+(offset), board));
        }

        //Generate left takes (This could probably be more efficient)
        if(xord/size-1 > 0 && board.getTiles().get(xord/size-1).get(yord/size+(offset)).getPiece() != null && board.getTiles().get(xord/size-1).get(yord/size+(offset)).getPiece().getIsWhite() != isWhite){
            moves.add(new Move(this, xord/size, yord/size+(offset), board));
        }

        //Generate right takes
        if(xord/size+1 < board.boardX && board.getTiles().get(xord/size+1).get(yord/size+(offset)).getPiece() != null && board.getTiles().get(xord/size+1).get(yord/size+(offset)).getPiece().getIsWhite() != isWhite){
            moves.add(new Move(this, xord/size, yord/size+(offset), board));
        }

        //Still needs en passant I don't think anything else is messing

        return moves;
    }

}
