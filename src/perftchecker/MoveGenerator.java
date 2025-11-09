package perftchecker;

import layout.Board;
import moves.Move;
import pieces.Piece;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveGenerator {

    private Board board;
    HashMap<Integer, Character> map;
    HashMap<Integer, Integer> map2;

    public MoveGenerator(Board board){
        this.board = board;
        HashMap<Integer, Character> map = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            map.put(i, (char) ('a' + i));
        }

        HashMap<Integer, Integer> map2 = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            map2.put(i, 8 - i);
        }

        this.map = map;
        this.map2 = map2;
    }

    public int generateAllMoves(int depth, Move moveHist){
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
                if(moveHist != null){
                    //System.out.println(map.get(moveHist.getOldX())+""+map2.get(moveHist.getOldY())+""+map.get(moveHist.getNewX())+""+map2.get(moveHist.getNewY())+": "+moves.size());
                }

                return moves.size();
            }
            for(Move move: moves){

                //System.out.println(map.get(move.getOldX())+""+map2.get(move.getOldY())+""+map.get(move.getNewX())+""+map2.get(move.getNewY())+": "+moves.size());

                move.execute();
                moveCount += generateAllMoves(depth -1, move);
                move.undo();
            }
            if(depth == 3 && moveHist!= null){
                System.out.println(map.get(moveHist.getOldX())+""+map2.get(moveHist.getOldY())+""+map.get(moveHist.getNewX())+""+map2.get(moveHist.getNewY())+": "+moveCount);
            }
            if(depth == 2 && moveHist!= null){
                System.out.println(map.get(moveHist.getOldX())+""+map2.get(moveHist.getOldY())+""+map.get(moveHist.getNewX())+""+map2.get(moveHist.getNewY())+": "+moveCount);
            }
        }
        return moveCount;
    }
}

