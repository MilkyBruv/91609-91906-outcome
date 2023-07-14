package entity.tile;

import java.awt.Rectangle;

import entity.Entity;
import event.GameEventManager;
import tileset.Spritesheet;

public class Tile extends Entity {
    
    public String id;
    public String type;

    public Tile(int x, int y, String id, GameEventManager game) {

        super(x, y, game);
        
        this.id = id;
        this.image = Spritesheet.getImage(id);
        this.type = Spritesheet.getType(id);
        this.rect = new Rectangle(0, 0, 8, 8);

    }



    @Override
    public void update() {

        this.drawX = this.x;
        this.drawY = this.y;

    }

}
