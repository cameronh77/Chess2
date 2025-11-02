package pieces;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Piece {
    protected BufferedImage image;
    protected String name;

    public int getXord() {
        return xord;
    }

    public void setXord(int xord) {
        this.xord = xord;
    }

    public int getYord() {
        return yord;
    }

    public void setYord(int yord) {
        this.yord = yord;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    protected int xord;
    protected int yord;
    protected boolean isWhite;
    protected int size;

    Piece(int x, int y, boolean isWhite, String name, int size){
        this.xord = x;
        this.yord = y;
        this.isWhite = isWhite;
        this.name = name;
        this.size = size;
        try {
            // Load the image from your resources folder (e.g., src/main/resources)
            String path = "resources/pieces/"+(isWhite?"white":"black")+"-"+name+".png";
            System.out.println(path);
            image = ImageIO.read(new File(path));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("Failed to load piece image.");
        }

    }

    public void draw(Graphics2D g){
        g.drawImage(image, xord*size, yord*size, size, size,  null);
        System.out.println("Drawing image"+ image);
    }
}
