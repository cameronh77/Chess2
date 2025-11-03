package pieces;

import layout.Board;
import moves.Move;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "bishop", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();

        //pos pos
        for(int offset = 1; offset + xord/size< board.boardX && offset + yord/size< board.boardY; offset+=1){
            if(board.getTiles().get(xord/size+offset).get(yord/size+offset).getPiece() != null){
                if(board.getTiles().get(xord/size+offset).get(yord/size+offset).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, xord/size+offset, yord/size+offset, board));
                }
                break;
            }else{
                moves.add(new Move(this, xord/size+offset, yord/size+offset, board));
            }
        }

        //pos neg
        for(int offset = 1; offset + xord/size< board.boardX && -offset + yord/size >=  0; offset+=1){
            if(board.getTiles().get(xord/size+offset).get(yord/size-offset).getPiece() != null){
                if(board.getTiles().get(xord/size+offset).get(yord/size-offset).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, xord/size+offset, yord/size-offset, board));
                }
                break;
            }else{
                moves.add(new Move(this, xord/size+offset, yord/size-offset, board));
            }
        }

        for(int offset = 1; -offset + xord/size >= 0 && offset < yord/size+ board.boardY; offset+=1){
            if(board.getTiles().get(xord/size-offset).get(yord/size+offset).getPiece() != null){
                if(board.getTiles().get(xord/size-offset).get(yord/size+offset).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, xord/size-offset, yord/size+offset, board));
                }
                break;
            }else{
                moves.add(new Move(this, xord/size-offset, yord/size+offset, board));
            }
        }

        for(int offset = 1; -offset + xord/size >= 0 && -offset + yord/size >=  0; offset+=1){
            if(board.getTiles().get(xord/size-offset).get(yord/size-offset).getPiece() != null){
                if(board.getTiles().get(xord/size-offset).get(yord/size-offset).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, xord/size-offset, yord/size-offset, board));
                }
                break;
            }else{
                moves.add(new Move(this, xord/size-offset, yord/size-offset, board));
            }
        }

        return moves;
    }

}
