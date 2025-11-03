package pieces;

import layout.Board;
import moves.Move;

import java.util.ArrayList;

public class Castle extends Piece{

    public Castle(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "castle", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board){
        ArrayList<Move> moves = new ArrayList<>();

        //pos x
        for(int col = xord/size +1; col < board.boardX; col += 1){
            if(board.getTiles().get(col).get(yord/size).getPiece() != null){
                if(board.getTiles().get(col).get(yord/size).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, col, yord/size, board));
                }
                break;
            }else{
                moves.add(new Move(this, col, yord/size, board));
            }
        }

        //pos y
        for(int row = yord/size +1; row < board.boardY; row += 1){
            if(board.getTiles().get(xord/size).get(row).getPiece() != null){
                if(board.getTiles().get(xord/size).get(row).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, xord/size, row, board));
                }
                break;
            }else{
                moves.add(new Move(this, xord/size, row, board));
            }
        }

        //neg x
        for(int col = xord/size -1; col >= 0; col -= 1){
            if(board.getTiles().get(col).get(yord/size).getPiece() != null){
                if(board.getTiles().get(col).get(yord/size).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, col, yord/size, board));
                }
                break;
            }else{
                moves.add(new Move(this, col, yord/size, board));
            }
        }

        //neg y
        for(int row = yord/size -1; row >= 0; row -= 1){
            if(board.getTiles().get(xord/size).get(row).getPiece() != null){
                if(board.getTiles().get(xord/size).get(row).getPiece().getIsWhite() != isWhite){
                    moves.add(new Move(this, xord/size, row, board));
                }
                break;
            }else{
                moves.add(new Move(this, xord/size, row, board));
            }
        }

        return moves;
    }
}
