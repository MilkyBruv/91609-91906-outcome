package entity.tile;

import java.awt.Rectangle;

import entity.Entity;
import gfx.ImageResource;
import tileset.Spritesheet;

public class Tile extends Entity {
    
    public String id;
    public ImageResource image;
    public String type;
    public Rectangle rect;

    public Tile(int x, int y, String id) {

        super(x, y);

        this.drawX = x;
        this.drawY = y;
        this.id = id;
        this.image = Spritesheet.getImage(id);
        this.type = Spritesheet.getType(id);
        this.rect = new Rectangle(0, 0, 8, 8);

    }

}
