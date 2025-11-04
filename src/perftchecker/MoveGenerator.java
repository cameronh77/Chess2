package perftchecker;

import layout.Board;
import moves.Move;
import pieces.Piece;

import java.util.ArrayList;

public class MoveGenerator {

    private Board board;

    public MoveGenerator(Board board){
        this.board = board;
    }

    public int generateAllMoves(int depth){
        int moveCount = 0;
        if(depth == 0){
            return 1;
        }
        else{
            ArrayList<Move> moves = new ArrayList<>();
            ArrayList<Piece> pieces = new ArrayList<>();
            pieces.addAll(board.getPieces());
            for(Piece piece: pieces) {
                moves.addAll(piece.generateMoves(board, false));
            }
            if(depth == 1){
                return moves.size();
            }
            for(Move move: moves){
                //System.out.println("depth " + depth + " movecount " + moveCount);
                //if(depth == 2 && moveCount == 125){
                //    System.out.println("HERE I AM");
                //}
                move.execute();
                moveCount += generateAllMoves(depth -1);
                move.undo();
            }
        }
        return moveCount;
    }
}

