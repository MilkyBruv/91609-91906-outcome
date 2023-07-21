package entity.tile;

import java.awt.Rectangle;

import entity.Entity;
import game.GameEventManager;
import tileset.Spritesheet;

public class Tile extends Entity {
    
    public String id;
    public String type;

    public Tile(int x, int y, String id, GameEventManager game) {

        super(x, y, 8, 8, game);
        
        this.id = id;
        this.image = Spritesheet.getImage(id);
        this.type = Spritesheet.getType(id);
        this.rect = new Rectangle();
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = 8;
        this.rect.height = 8;

    }



    @Override
    public void update() {

        this.drawX = this.x;
        this.drawY = this.y;

        this.rect.x = this.x;
        this.rect.y = this.y;

    }

}
