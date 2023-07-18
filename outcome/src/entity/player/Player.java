package entity.player;

import java.awt.Rectangle;

import com.jogamp.newt.event.KeyEvent;

import entity.Entity;
import entity.tile.Tile;
import event.KeyInfo;
import game.GameEventManager;
import game.GameSettings;
import gfx.Renderer;
import tileset.Spritesheet;

public final class Player extends Entity {

    public Rectangle rect;
    public int width = 8;
    public int height = 8;
    public int speed = 2;
    public float velX = 0;
    public float velY = 0;

    public boolean collidedUp = false;
    public boolean collidedLeft = false;
    public boolean collidedDown = false;
    public boolean collidedRight = false;

    public boolean canJump = false;

    public Player(int x, int y, GameEventManager game) {
        
        super(x, y, game);
        
        this.rect = new Rectangle();
        this.rect.x = this.x;
        this.rect.y = this.y;
        this.rect.width = 8;
        this.rect.height = 8;
        this.image = Spritesheet.getImage("2");
        
    }
    


    @Override
    public void update() {

        this.drawX = this.x;
        this.drawY = this.y;

        // Call update methods
        this.applyGravity();
        this.applyXVelocity();

        this.rect.x = this.x;
        this.detectTileCollisions("x");
        this.rect.y = this.y;
        this.detectTileCollisions("y");

        this.getKeyInput();

    }



    public final void detectTileCollisions(String dir) {

        // Detect collisions in determined direction (x or y)
        if (dir.equals("x")) {

            // Loop through each tile and check if it is solid
            for (Tile tile : this.game.tilemap.getTiles()) {
                
                if (tile.type.equals("solid")) {

                    // If the tile is solid and the player is moving, set the position to align with the tile and zero
                    // the player velocity
                    if (this.rect.intersects(tile.rect)) {

                        if (this.velX < 0) {

                            this.x = tile.x + GameSettings.TILESIZE;
                            this.collidedLeft = true;

                        }

                        if (this.velX > 0) {

                            this.x = tile.x - GameSettings.TILESIZE;
                            this.collidedRight = true;

                        }

                        this.rect.x = this.x;
                        this.velX = 0;

                    } else {

                        // Set x collision properties to false if no collisions happen
                        this.collidedLeft = false;
                        this.collidedRight = false;

                    }

                }

            }

        }

        if (dir.equals("y")) {

            // Loop through each tile and check if it is solid
            for (Tile tile : this.game.tilemap.getTiles()) {
                
                if (tile.type.equals("solid")) {

                    // If the tile is solid and the player collides with it, set the position to align with the tile and 
                    // zero the player velocity
                    if (this.rect.intersects(tile.rect)) {

                        if (this.velY < 0) {

                            this.y = tile.y + GameSettings.TILESIZE;
                            this.collidedUp = true;

                        }

                        if (this.velY > 0) {

                            this.y = tile.y - GameSettings.TILESIZE;
                            this.collidedDown = true;

                        }

                        this.rect.y = this.y;
                        this.velY = 0;

                    } else {

                        this.collidedDown = false;
                        this.collidedUp = false;

                    }

                }

            }

        }

    }



    /**
     * Applies gravitational calculations to y velocity, then in turn position
     */
    public final void applyGravity() {

        // Apply gravitational calculations
        this.velY += GameSettings.GRAVITY;
        this.velY += GameSettings.GRAVITY * 0.5f;
        this.y += this.velY;

    }



    /**
     * Applies x velocity to position
     */
    public void applyXVelocity() {

        this.x += this.velX;

    }



    /**
     * Gets input from keyboard
     */
    public final void getKeyInput() {

        // Zero x velocity to stop player if no x input is detected
        this.velX = 0;

        if (KeyInfo.isKeyPressed(KeyEvent.VK_LEFT) && !this.collidedLeft) {

            this.velX = -this.speed;

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_RIGHT) && !this.collidedRight) {

            this.velX = this.speed;

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_C)) {

            this.jump();

        }

        if (KeyInfo.isKeyPressed(KeyEvent.VK_0)) {

            this.x = 32;
            this.y = 32;
            this.velX = 0;
            this.velY = 0;

        }

    }



    /**
     * Causes player to jump
     */
    public final void jump() {

        if (this.collidedDown) {

            this.velY = -3;
            this.collidedDown = false;

        }

    }



    public void draw() {

        Renderer.drawImage(this.image, this.drawX, this.drawY);

    }
    
}
