package layout;

import moves.BoardInput;
import moves.Move;
import perftchecker.MoveGenerator;
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
    private int[] enPassantTile = new int[2];

    private Boolean whiteToMove = true;


    public Board (){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        this.setSize(new Dimension((int) Math.floor(0.6*screenHeight), (int) Math.floor(0.6*screenHeight)));
        this.tileSize = (int) Math.floor(0.6*screenHeight) / 8;
        for(int c=0; c < boardX; c ++){
            ArrayList<Tile> col = new ArrayList();
            for(int r =0; r < boardY; r++){
                col.add(r, new Tile(c, r, tileSize));
            }
            tiles.add(c, col);
        }
        //addTestPieces();
        //addKiwipetePieces();

        loadPositionFromFEN("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P3/2N2Q1p/PPPBBPPP/R3K2R w KQkq - "); //Position 2 too much on perft 3
        //loadPositionFromFEN("r3k2r/p1ppqpb1/bn2pnp1/3PN3/1p2P1P1/2N2Q1p/PPPBBP1P/R3K2R b KQkq g3 0 1");

        //loadPositionFromFEN("8/2p5/3p4/KP5r/1R3p1k/8/4P1P1/8 w - - 0 1 "); //Position 3 just short on perft 6

        //loadPositionFromFEN("r3k2r/Pppp1ppp/1b3nbN/nP6/BBP1P3/q4N2/Pp1P2PP/R2Q1RK1 w kq - 0 1");
        //loadPositionFromFEN("r3k2r/Pppp1ppp/1b3nbN/nPB5/B1P1P3/q4N2/Pp1P2PP/R2Q1RK1 b kq - 0 1");



        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        MoveGenerator moveGenerator = new MoveGenerator(this);

        long start = System.currentTimeMillis(); // start timer

        // Simulate work
        System.out.println(moveGenerator.generateAllMoves(4, null));

        long end = System.currentTimeMillis();   // stop timer
        long elapsed = end - start;
        System.out.println(elapsed);
    }

    public void addToBoard(Piece piece){
        pieces.add(piece);
        tiles.get(piece.getCol()).get(piece.getRow()).setPiece(piece);
    }


    public Boolean checkEvaluator(Move move){
        move.execute();
        Boolean isKingChecked = false;
        for(Piece piece: pieces){
            //System.out.println(piece);
            ArrayList<Move> moves = piece.generateMoves(this, true);
            for(Move subMove: moves){
                if(subMove.getCapturedPiece() != null && subMove.getCapturedPiece().getName() == "king"){
                    isKingChecked = true;
                }
            }
        }

        move.undo();
        return isKingChecked;
    }

    public Boolean tileCheckEvaluator(Tile tile){
        Boolean isTileChecked = false;
        whiteToMove = !whiteToMove;
        for(Piece piece: pieces){
            if(!(piece.getName()=="king")){
                ArrayList<Move> moves = piece.generateMoves(this, true);
                for(Move subMove: moves){
                    if(subMove.getNewX() == tile.getXord() && subMove.getNewY() == tile.getYord()){
                        isTileChecked = true;
                    }
                }
            }

        }
        whiteToMove = !whiteToMove;
        return isTileChecked;
    }

    public void addKiwipetePieces() {
        addToBoard(new Castle(0, 0, false, tileSize)); // a8: black rook
        addToBoard(new King(4, 0, false, tileSize));   // e8: black king
        addToBoard(new Castle(7, 0, false, tileSize)); // h8: black rook

        // Rank 7 (row 1)
        addToBoard(new Pawn(0, 1, false, tileSize));   // a7
        addToBoard(new Pawn(2, 1, false, tileSize));   // c7
        addToBoard(new Pawn(3, 1, false, tileSize));   // d7
        addToBoard(new Queen(4, 1, false, tileSize));  // e7
        addToBoard(new Pawn(5, 1, false, tileSize));   // f7
        addToBoard(new Bishop(6, 1, false, tileSize)); // g7

        // Rank 6 (row 2)
        addToBoard(new Bishop(0, 2, false, tileSize)); // a6
        addToBoard(new Horse(1, 2, false, tileSize));  // b6
        addToBoard(new Pawn(4, 2, false, tileSize));   // e6
        addToBoard(new Horse(5, 2, false, tileSize));  // f6
        addToBoard(new Pawn(6, 2, false, tileSize));   // g6

        // Rank 5 (row 3)
        addToBoard(new Pawn(3, 3, true, tileSize));    // d5 (white pawn)
        addToBoard(new Horse(4, 3, true, tileSize));   // e5 (white knight)

        // Rank 4 (row 4)
        addToBoard(new Pawn(1, 4, false, tileSize));   // b4 (black pawn)
        addToBoard(new Pawn(4, 4, true, tileSize));    // e4 (white pawn)

        // Rank 3 (row 5)
        addToBoard(new Horse(2, 5, true, tileSize));   // c3 (white knight)
        addToBoard(new Queen(5, 5, true, tileSize));   // f3 (white queen)
        addToBoard(new Pawn(7, 5, false, tileSize));   // h3 (black pawn)

        // Rank 2 (row 6)
        addToBoard(new Pawn(0, 6, true, tileSize));    // a2
        addToBoard(new Pawn(1, 6, true, tileSize));    // b2
        addToBoard(new Pawn(2, 6, true, tileSize));    // c2
        addToBoard(new Bishop(3, 6, true, tileSize));  // d2
        addToBoard(new Bishop(4, 6, true, tileSize));  // e2
        addToBoard(new Pawn(5, 6, true, tileSize));    // f2
        addToBoard(new Pawn(6, 6, true, tileSize));    // g2
        addToBoard(new Pawn(7, 6, true, tileSize));    // h2

        // Rank 1 (row 7)
        addToBoard(new Castle(0, 7, true, tileSize));  // a1: white rook
        addToBoard(new King(4, 7, true, tileSize));    // e1: white king
        addToBoard(new Castle(7, 7, true, tileSize));  // h1: white rook
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
        validMoves = selectedPiece.generateMoves(this, false);

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

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public ArrayList<Move> getValidMoves(){
        return validMoves;
    }

    public void resetValidMoves(){
        validMoves = null;
    }

    public int[] getEnPassantTile(){
        return enPassantTile;
    }

    public void setEnPassantTile(int[] enPassantTile){
        this.enPassantTile = enPassantTile;
    }

    public Boolean getWhiteToMove(){
        return whiteToMove;
    }

    public void setWhiteToMove(Boolean whiteToMove){
        this.whiteToMove = whiteToMove;
    }

    public void loadPositionFromFEN(String fenString){
        pieces.clear();
        String[] parts = fenString.split(" ");

        //set up pieces
        String position = parts[0];
        int row = 0;
        int col = 0;
        for (int i = 0; i <position.length(); i++){
            char ch = position.charAt(i);
            if(ch == '/'){
                row++;
                col = 0;
            } else if (Character.isDigit(ch)) {
                col += Character.getNumericValue(ch);
            } else {
                boolean isWhite = Character.isUpperCase(ch);
                char pieceChar = Character.toLowerCase(ch);

                switch(pieceChar){
                    case 'r':
                        tiles.get(col).get(row).setPiece(new Castle(col, row, isWhite, tileSize));
                        addToBoard(tiles.get(col).get(row).getPiece());
                        break;
                    case 'n':
                        tiles.get(col).get(row).setPiece(new Horse(col, row, isWhite, tileSize));
                        addToBoard(tiles.get(col).get(row).getPiece());
                        break;
                    case 'b':
                        tiles.get(col).get(row).setPiece(new Bishop(col, row, isWhite, tileSize));
                        addToBoard(tiles.get(col).get(row).getPiece());
                        break;
                    case 'q':
                        tiles.get(col).get(row).setPiece(new Queen(col, row, isWhite, tileSize));
                        addToBoard(tiles.get(col).get(row).getPiece());
                        break;
                    case 'k':
                        tiles.get(col).get(row).setPiece(new King(col, row, isWhite, tileSize));
                        addToBoard(tiles.get(col).get(row).getPiece());
                        break;
                    case 'p':
                        Piece pawn = new Pawn(col, row, isWhite, tileSize);
                        if((pawn.getIsWhite() && pawn.getRow() != 6) || (!pawn.getIsWhite() && pawn.getRow() != 1)){
                            pawn.setIsFirstMove(false);
                        }
                        addToBoard(pawn);
                        tiles.get(col).get(row).setPiece(pawn);
                        break;
                }
                col++;
            }
        }

        whiteToMove = parts[1].equals("w");


        Piece bqr = tiles.get(0).get(0).getPiece();
        if (bqr instanceof Castle){
            bqr.setIsFirstMove(parts[2].contains("q"));
        }

        Piece bkr = tiles.get(7).get(0).getPiece();
        if (bkr instanceof Castle){
            bkr.setIsFirstMove(parts[2].contains("k"));
        }

        Piece wqr = tiles.get(0).get(7).getPiece();
        if (wqr instanceof Castle){
            wqr.setIsFirstMove(parts[2].contains("Q"));
        }

        Piece wkr = tiles.get(7).get(7).getPiece();
        if (wkr instanceof Castle){
            wkr.setIsFirstMove(parts[2].contains("K"));
        }


        if (parts[3].equals("-")) {
            enPassantTile = null;
        } else {
            int file = parts[3].charAt(0) - 'a';           // column 0–7
            int rank = 7 - (parts[3].charAt(1) - '1');     // row 0–7 (top-down)
            int[] enPassantArray = {rank, file};
            enPassantTile = enPassantArray;
        }
    }
}
