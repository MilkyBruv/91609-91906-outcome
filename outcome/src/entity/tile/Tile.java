package entity.tile;

import java.awt.Rectangle;

import entity.Entity;
import game.GameEventManager;
import tileset.Spritesheet;

public class Tile extends Entity {
    
    public String id;
    public String type;
    public int width = 8;
    public int height = 8;

    public Tile(int x, int y, String id, GameEventManager game) {

        super(x, y, game);
        
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

        this.setDrawPosition();

        this.rect.x = this.x;
        this.rect.y = this.y;

    }



    /**
     * Check if the Camera is locked to the edge of the map and adjust draw position to match Camera bounds
     */
    public final void setDrawPosition() {

        if (this.game.camera.lockedLeft) {

            this.drawX = this.x;

        } else if (this.game.camera.lockedRight) {

            this.drawX = this.x - this.game.tilemap.getTmxInfo().getTotalWidth() / 2;

        } else {

            // Set draw position relative to player x position
            this.drawX = this.x - this.game.player.x + this.game.player.drawX;

        }

        if (this.game.camera.lockedTop) {

            this.drawY = this.y;

        } else if (this.game.camera.lockedBottom) {

            this.drawY = this.y - this.game.tilemap.getTmxInfo().getTotalHeight() / 2;

        } else {

            // Set draw position relative to player x position
            this.drawY = this.y - this.game.player.y + this.game.player.drawY;

        }

    }

}
