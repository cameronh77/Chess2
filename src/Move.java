import pieces.Piece;

public class Move {
    private int oldX, oldY;
    private int newX, newY;
    private Board board;

    public Move(Piece piece, int newX, int newY, Board board){
        this.oldX = piece.getXord();
        this.oldY = piece.getYord();
        this.newX = newX;
        this.newY = newY;
    }






}
