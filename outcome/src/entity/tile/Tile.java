package entity.tile;

import java.awt.Rectangle;

import gfx.ImageResource;

public class Tile {
    
    public int x;
    public int y;
    public String id;
    public ImageResource image;
    public Rectangle rect;

    public Tile(int x, int y, String id) {

        this.x = x;
        this.y = y;
        this.id = id;

    }

}
