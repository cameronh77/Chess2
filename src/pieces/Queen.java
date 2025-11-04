package pieces;

import layout.Board;
import moves.Move;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(int x, int y, boolean isWhite, int size){
        super(x, y, isWhite, "queen", size);

    }

    @Override
    public ArrayList<Move> generateMoves(Board board, Boolean ignoreCheck){
        ArrayList<Move> moves = new ArrayList<>();
        if(isWhite == board.getWhiteToMove()) {
            //pos pos
            for (int offset = 1; offset + xord / size < board.boardX && offset + yord / size < board.boardY; offset += 1) {
                if (board.getTiles().get(xord / size + offset).get(yord / size + offset).getPiece() != null) {
                    if (board.getTiles().get(xord / size + offset).get(yord / size + offset).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, xord / size + offset, yord / size + offset, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, xord / size + offset, yord / size + offset, board));
                }
            }

            //pos neg
            for (int offset = 1; offset + xord / size < board.boardX && -offset + yord / size >= 0; offset += 1) {
                if (board.getTiles().get(xord / size + offset).get(yord / size - offset).getPiece() != null) {
                    if (board.getTiles().get(xord / size + offset).get(yord / size - offset).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, xord / size + offset, yord / size - offset, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, xord / size + offset, yord / size - offset, board));
                }
            }

            for (int offset = 1; -offset + xord / size >= 0 && offset + yord / size < board.boardY; offset += 1) {
                if (board.getTiles().get(xord / size - offset).get(yord / size + offset).getPiece() != null) {
                    if (board.getTiles().get(xord / size - offset).get(yord / size + offset).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, xord / size - offset, yord / size + offset, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, xord / size - offset, yord / size + offset, board));
                }
            }

            for (int offset = 1; -offset + xord / size >= 0 && -offset + yord / size >= 0; offset += 1) {
                if (board.getTiles().get(xord / size - offset).get(yord / size - offset).getPiece() != null) {
                    if (board.getTiles().get(xord / size - offset).get(yord / size - offset).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, xord / size - offset, yord / size - offset, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, xord / size - offset, yord / size - offset, board));
                }
            }

            //pos x
            for (int col = xord / size + 1; col < board.boardX; col += 1) {
                if (board.getTiles().get(col).get(yord / size).getPiece() != null) {
                    if (board.getTiles().get(col).get(yord / size).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, col, yord / size, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, col, yord / size, board));
                }
            }

            //pos y
            for (int row = yord / size + 1; row < board.boardY; row += 1) {
                if (board.getTiles().get(xord / size).get(row).getPiece() != null) {
                    if (board.getTiles().get(xord / size).get(row).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, xord / size, row, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, xord / size, row, board));
                }
            }

            //neg x
            for (int col = xord / size - 1; col >= 0; col -= 1) {
                if (board.getTiles().get(col).get(yord / size).getPiece() != null) {
                    if (board.getTiles().get(col).get(yord / size).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, col, yord / size, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, col, yord / size, board));
                }
            }

            //neg y
            for (int row = yord / size - 1; row >= 0; row -= 1) {
                if (board.getTiles().get(xord / size).get(row).getPiece() != null) {
                    if (board.getTiles().get(xord / size).get(row).getPiece().getIsWhite() != isWhite) {
                        moves.add(new Move(this, xord / size, row, board));
                    }
                    break;
                } else {
                    moves.add(new Move(this, xord / size, row, board));
                }
            }
            if(!ignoreCheck){
                //Ignore the naming convention for now
                ArrayList<Move> trueMoves = new ArrayList<>();
                for (Move move : moves) {
                    if (!board.checkEvaluator(move)) {
                        trueMoves.add(move);
                    }
                }
                return trueMoves;
            }
            return moves;
        } else {
            return moves;
        }
    }

}
