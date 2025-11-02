import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    static int boardX = 8;
    static int boardY = 8;
    private int tileSize;
    private ArrayList<Piece> pieces = new ArrayList<>();

    private ArrayList<Tile> tiles = new ArrayList();

    public Board (){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        this.setSize(new Dimension((int) Math.floor(0.6*screenHeight), (int) Math.floor(0.6*screenHeight)));
        this.tileSize = (int) Math.floor(0.6*screenHeight) / 8;
        for(int r=0; r < boardX; r ++){
            for(int c =0; c < boardY; c++){
                tiles.add(new Tile(r, c, tileSize));
            }
        }
        addTestPieces();
    }

    public void addTestPieces(){
        pieces.add(new Castle(0, 0, false, tileSize));
        pieces.add(new Horse(1, 0, false, tileSize));
        pieces.add(new Bishop(2, 0, false, tileSize));
        pieces.add(new Queen(3, 0, false, tileSize));
        pieces.add(new King(4, 0, false, tileSize));
        pieces.add(new Bishop(5, 0, false, tileSize));
        pieces.add(new Horse(6, 0, false, tileSize));
        pieces.add(new Castle(7, 0, false, tileSize));

        pieces.add(new Pawn(0, 1, false, tileSize));
        pieces.add(new Pawn(1, 1, false, tileSize));
        pieces.add(new Pawn(2, 1, false, tileSize));
        pieces.add(new Pawn(3, 1, false, tileSize));
        pieces.add(new Pawn(4, 1, false, tileSize));
        pieces.add(new Pawn(5, 1, false, tileSize));
        pieces.add(new Pawn(6, 1, false, tileSize));
        pieces.add(new Pawn(7, 1, false, tileSize));

        pieces.add(new Castle(0, 7, true, tileSize));
        pieces.add(new Horse(1, 7, true, tileSize));
        pieces.add(new Bishop(2, 7, true, tileSize));
        pieces.add(new Queen(3, 7, true, tileSize));
        pieces.add(new King(4, 7, true, tileSize));
        pieces.add(new Bishop(5, 7, true, tileSize));
        pieces.add(new Horse(6, 7, true, tileSize));
        pieces.add(new Castle(7, 7, true, tileSize));

        pieces.add(new Pawn(0, 6, true, tileSize));
        pieces.add(new Pawn(1, 6, true, tileSize));
        pieces.add(new Pawn(2, 6, true, tileSize));
        pieces.add(new Pawn(3, 6, true, tileSize));
        pieces.add(new Pawn(4, 6, true, tileSize));
        pieces.add(new Pawn(5, 6, true, tileSize));
        pieces.add(new Pawn(6, 6, true, tileSize));
        pieces.add(new Pawn(7, 6, true, tileSize));

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for(Tile tile: tiles){
            tile.draw(g2d);
        }
        for(Piece piece: pieces){
            piece.draw(g2d);
        }
    }
}
