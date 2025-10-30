import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    static int boardX = 8;
    static int boardY = 8;
    private int tileSize;

    private ArrayList<Tile> tiles = new ArrayList();

    Board (){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;

        this.setSize(new Dimension((int) Math.floor(0.9*screenHeight), (int) Math.floor(0.9*screenHeight)));
        this.tileSize = (int) Math.floor(0.9*screenHeight) / 8;
        for(int r=0; r < boardX; r ++){
            for(int c =0; c < boardY; c++){
                tiles.add(new Tile(r, c, tileSize));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        for(Tile tile: tiles){
            tile.draw(g);
        }
    }
}
