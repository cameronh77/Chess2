import layout.Board;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = screenSize.height;


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension((int) Math.floor(0.7*screenHeight), (int) Math.floor(0.7*screenHeight)));

        Board board = new Board();
        frame.add(board);
        frame.setVisible(true);
    }
}