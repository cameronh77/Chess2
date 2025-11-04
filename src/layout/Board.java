package layout;

import moves.BoardInput;
import moves.Move;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    public int boardX = 8;
    public int boardY = 8;
    private int tileSize;
    private ArrayList<Piece> pieces = new ArrayList<>();
    BoardInput input = new BoardInput(this);

    private ArrayList<ArrayList<Tile>> tiles = new ArrayList();

    private Piece selectedPiece;
    private ArrayList<Move> validMoves;


    public Board (){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        this.setSize(new Dimension((int) Math.floor(0.6*screenHeight), (int) Math.floor(0.6*screenHeight)));
        this.tileSize = (int) Math.floor(0.6*screenHeight) / 8;
        for(int r=0; r < boardX; r ++){
            ArrayList<Tile> row = new ArrayList();
            for(int c =0; c < boardY; c++){
                row.add(c, new Tile(r, c, tileSize));
            }
            tiles.add(r, row);
        }
        addTestPieces();
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }

    public void addToBoard(Piece piece){
        pieces.add(piece);
        tiles.get(piece.getXord()/tileSize).get(piece.getYord()/tileSize).setPiece(piece);
    }

    public void addTestPieces(){
        addToBoard(new Castle(0, 0, false, tileSize));
        addToBoard(new Horse(1, 0, false, tileSize));
        addToBoard(new Bishop(2, 0, false, tileSize));
        addToBoard(new Queen(3, 0, false, tileSize));
        addToBoard(new King(4, 0, false, tileSize));
        addToBoard(new Bishop(5, 0, false, tileSize));
        addToBoard(new Horse(6, 0, false, tileSize));
        addToBoard(new Castle(7, 0, false, tileSize));

        addToBoard(new Pawn(0, 1, false, tileSize));
        addToBoard(new Pawn(1, 1, false, tileSize));
        addToBoard(new Pawn(2, 1, false, tileSize));
        addToBoard(new Pawn(3, 1, false, tileSize));
        addToBoard(new Pawn(4, 1, false, tileSize));
        addToBoard(new Pawn(5, 1, false, tileSize));
        addToBoard(new Pawn(6, 1, false, tileSize));
        addToBoard(new Pawn(7, 1, false, tileSize));

        addToBoard(new Pawn(0, 6, true, tileSize));
        addToBoard(new Pawn(1, 6, true, tileSize));
        addToBoard(new Pawn(2, 6, true, tileSize));
        addToBoard(new Pawn(3, 6, true, tileSize));
        addToBoard(new Pawn(4, 6, true, tileSize));
        addToBoard(new Pawn(5, 6, true, tileSize));
        addToBoard(new Pawn(6, 6, true, tileSize));
        addToBoard(new Pawn(7, 6, true, tileSize));

        addToBoard(new Castle(0, 7, true, tileSize));
        addToBoard(new Horse(1, 7, true, tileSize));
        addToBoard(new Bishop(2, 7, true, tileSize));
        addToBoard(new Queen(3, 7, true, tileSize));
        addToBoard(new King(4, 7, true, tileSize));
        addToBoard(new Bishop(5, 7, true, tileSize));
        addToBoard(new Horse(6, 7, true, tileSize));
        addToBoard(new Castle(7, 7, true, tileSize));
    }

    public int getTileSize(){
        return tileSize;
    }

    public void generatePieceMoves(){
        validMoves = selectedPiece.generateMoves(this);

    }
    public ArrayList<ArrayList<Tile>> getTiles(){
        return tiles;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(ArrayList<Tile> row: tiles){
            for(Tile tile: row) {
                tile.draw(g2d);
            }
        }
        for(Piece piece: pieces){
            piece.draw(g2d);
        }

        if(selectedPiece != null){
            for(Move move: validMoves){
                g2d.setColor(Color.RED);
                g2d.fillRect(move.getNewX()*tileSize, move.getNewY()*tileSize, tileSize, tileSize);
            }
        }
    }

    public void setSelectedPiece(Piece piece){
        this.selectedPiece = piece;
    }

    public Piece getSelectedPiece(){
        return selectedPiece;
    }
}
