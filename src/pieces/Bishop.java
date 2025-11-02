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
        //This is incredibly incorrect, its doing more than just diagonals
        for(int r = yord/size+1; r< board.boardY; r+=1){
            for(int c = xord/size+1; c< board.boardX; c+=1){
                //If no piece generate as normal else evaluate take
                if(board.getTiles().get(c).get(r).getPiece() != null){
                    if(board.getTiles().get(c).get(r).getPiece().getIsWhite() != isWhite){
                        moves.add(new Move(this, c, r, board));
                    }
                    break;
                }else{
                    moves.add(new Move(this, c, r, board));
                }
            }
        }

        for(int r = yord/size+1; r< board.boardY; r+=1){
            for(int c = xord/size-1; c>=0 ; c-=1){
                //If no piece generate as normal else evaluate take
                if(board.getTiles().get(c).get(r).getPiece() != null){
                    if(board.getTiles().get(c).get(r).getPiece().getIsWhite() != isWhite){
                        moves.add(new Move(this, c, r, board));
                    }
                    break;
                }else{
                    moves.add(new Move(this, c, r, board));
                }
            }
        }

        for(int r = yord/size-1; r>=0; r-=1){
            for(int c = xord/size+1; c< board.boardX; c+=1){
                //If no piece generate as normal else evaluate take
                if(board.getTiles().get(c).get(r).getPiece() != null){
                    if(board.getTiles().get(c).get(r).getPiece().getIsWhite() != isWhite){
                        moves.add(new Move(this, c, r, board));
                    }
                    break;
                }else{
                    moves.add(new Move(this, c, r, board));
                }
            }
        }

        for(int r = yord/size-1; r>=0; r-=1){
            for(int c = xord/size-1; c>=0 ; c-=1){
                //If no piece generate as normal else evaluate take
                if(board.getTiles().get(c).get(r).getPiece() != null){
                    if(board.getTiles().get(c).get(r).getPiece().getIsWhite() != isWhite){
                        moves.add(new Move(this, c, r, board));
                    }
                    break;
                }else{
                    moves.add(new Move(this, c, r, board));
                }
            }
        }

        return moves;
    }

}
