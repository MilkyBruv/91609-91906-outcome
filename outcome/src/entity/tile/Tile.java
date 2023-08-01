package entity.tile;

import java.awt.Rectangle;

import entity.Entity;
import game.GameEventManager;
import game.GameSettings;
import tileset.Spritesheet;

public class Tile extends Entity {
    
    public String id;
    public String type;

    public Tile(int x, int y, String id, GameEventManager game) {

        super(x, y, game);
        
        this.id = id;
        this.image = Spritesheet.getImage(id);
        this.type = Spritesheet.getType(id);
        this.rect = new Rectangle();
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = GameSettings.TILESIZE;
        this.rect.height = GameSettings.TILESIZE;
        this.width = 8;
        this.height = 8;

    }



    @Override
    public void update() {

        this.setDrawPosition();

        this.rect.x = this.x;
        this.rect.y = this.y;

    }



    /**
     * Check if the Camera is locked to the edge of the map and adjust draw position to match Camera bounds
     */
    public final void setDrawPosition() {

        // Set draw position relative to player position
        this.drawX = this.x - this.game.player.x + this.game.player.drawX;
        this.drawY = this.y - this.game.player.y + this.game.player.drawY;

    }

}
