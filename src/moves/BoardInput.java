package moves;

import layout.Board;
import layout.Tile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardInput extends MouseAdapter {

    Board board;

    public BoardInput(Board board){
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e){
        int col = e.getX()/board.getTileSize();
        int row = e.getY()/board.getTileSize();
        Tile tile = board.getTiles().get(col).get(row);

        if(tile.getPiece() != null){

            board.setSelectedPiece(tile.getPiece());
            board.generatePieceMoves();

        }
    }

    @Override
    public void mouseDragged(MouseEvent e){
        if(board.getSelectedPiece() != null){
            board.getSelectedPiece().setXord(e.getX()- board.getTileSize()/2);
            board.getSelectedPiece().setYord(e.getY()- board.getTileSize()/2);

            board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        if(board.getSelectedPiece() != null){
            Move attemptedMove = new Move(board.getSelectedPiece(), e.getX()/ board.getTileSize(), e.getY()/ board.getTileSize(), board);
            Boolean executed = false;
            for(Move move: board.getValidMoves()){
                if(move.getNewX() == attemptedMove.getNewX() && move.getNewY() == attemptedMove.getNewY() && move.getMovingPiece() == attemptedMove.getMovingPiece()){
                    move.execute();
                    executed = true;
                    break;
                }
            }
            if(!executed){
                board.getSelectedPiece().setCol(board.getSelectedPiece().getCol());
                board.getSelectedPiece().setRow(board.getSelectedPiece().getRow());
            }
            board.setSelectedPiece(null);
            board.resetValidMoves();
            board.repaint();
        }
    }
}
